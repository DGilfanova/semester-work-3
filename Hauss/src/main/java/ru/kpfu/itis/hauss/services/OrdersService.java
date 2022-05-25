package ru.kpfu.itis.hauss.services;

import ru.kpfu.itis.hauss.dto.*;

import java.util.List;

public interface OrdersService {
    OrderDto getOrderById(Long orderId);
    List<OrderDto> getUserOrders(Long authUserId);
    OrderDto addOrder(OrderCreationDto newData, Long authUserId);
    OrderCreationDto prepareDefaultCreationForm(Long offerId);
    OrderCreationDto getUserOrderWithNewCurrencies(ChangeCurrencyDto currencyDto, Long offerId);
    List<OrderDto> getNearestOrderedServices(Long clientId);
    List<OrderDto> getNearestOrders(Long professionalId);
}
