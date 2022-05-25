package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.hauss.dto.*;
import ru.kpfu.itis.hauss.exceptions.*;
import ru.kpfu.itis.hauss.helpers.processors.OrderDateException;
import ru.kpfu.itis.hauss.helpers.processors.OrderDateProcessor;
import ru.kpfu.itis.hauss.models.*;
import ru.kpfu.itis.hauss.repositories.AccountsRepository;
import ru.kpfu.itis.hauss.repositories.CurrencyRepository;
import ru.kpfu.itis.hauss.repositories.OffersRepository;
import ru.kpfu.itis.hauss.repositories.OrdersRepository;
import ru.kpfu.itis.hauss.services.OrdersService;
import ru.kpfu.itis.hauss.utils.api.currencies.CurrencyConverter;

import java.time.LocalDate;
import java.util.List;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.DEFAULT_CURRENCY;

@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements OrdersService {

    private static final Double SITE_TAX = 1.02;

    private final OrdersRepository ordersRepository;
    private final AccountsRepository accountsRepository;
    private final OffersRepository offersRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    private final CurrencyConverter currencyConverter;

    @Autowired
    private final OrderDateProcessor orderDateProcessor;

    @Override
    public OrderDto getOrderById(Long orderId) {
        return OrderDto.from(ordersRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found")));
    }

    @Override
    public List<OrderDto> getUserOrders(Long authUserId) {
        return OrderDto.from(ordersRepository.findAllByAccountId(authUserId));
    }

    @Override
    public OrderCreationDto getUserOrderWithNewCurrencies(ChangeCurrencyDto currencyDto, Long offerId) {
        OrderCreationDto orderCreationDto = prepareDefaultCreationForm(offerId);

        Currency currencyFrom = currencyRepository.findById(currencyDto.getCurrencyId())
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found"));

        Currency currencyTo = currencyRepository.findById(currencyDto.getNewCurrencyId())
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found"));

        Double newPrice = currencyConverter.convert(currencyTo.getName(), currencyFrom.getName(), currencyDto.getTotalPrice());

        orderCreationDto.setCurrencyId(currencyTo.getId());
        orderCreationDto.setCurrencyName(currencyTo.getName());
        orderCreationDto.setTotalPrice(newPrice);
        return orderCreationDto;
    }

    @Override
    public List<OrderDto> getNearestOrderedServices(Long clientId) {
        return OrderDto.from(ordersRepository.findAllByAccountIdAndDateAfterOrderByDate(clientId, LocalDate.now()));
    }

    @Override
    public List<OrderDto> getNearestOrders(Long professionalId) {
        return OrderDto.from(ordersRepository.findAllByOfferAccountIdAndDateAfterOrderByDate(
                professionalId, LocalDate.now()));
    }

    @Transactional
    @Override
    public OrderDto addOrder(OrderCreationDto newData, Long authUserId) {

        try {
            orderDateProcessor.processDate(newData.getDate());
        } catch (OrderDateException e) {
            throw new CreateOrderException(e.getMessage(), e);
        }

        List<Order> conflictOrder = ordersRepository.findConflictOrder(newData.getOfferId(), newData.getDate());
        if (conflictOrder.size() > 0) {
            throw new CreateOrderException("Order has already been made for your time (" +
                    conflictOrder.get(0).getDate() + " + " + conflictOrder.get(0).getOffer().getExecutionTime() + " days)");
        }

        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        Offer offer = offersRepository.findById(newData.getOfferId())
                .orElseThrow(() -> new OfferNotFoundException("Offer not found"));

        Currency currency = currencyRepository.findById(newData.getCurrencyId())
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found"));

        return OrderDto.from(ordersRepository.save(
                Order.builder()
                        .account(account)
                        .offer(offer)
                        .date(newData.getDate())
                        .totalPrice(newData.getTotalPrice())
                        .currency(currency)
                        .comment(newData.getComment())
                        .build()
        ));
    }

    @Override
    public OrderCreationDto prepareDefaultCreationForm(Long offerId) {
        Offer offer = offersRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("Offer not found"));

        Currency currency = currencyRepository.findByName(DEFAULT_CURRENCY)
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found"));

        return OrderCreationDto.builder()
                .offerId(offerId)
                .totalPrice(getTotalPrice(offer.getPrice()))
                .currencyId(currency.getId())
                .currencyName(currency.getName())
                .build();
    }

    private Double getTotalPrice(Double price) {
        return price * SITE_TAX;
    }
}

