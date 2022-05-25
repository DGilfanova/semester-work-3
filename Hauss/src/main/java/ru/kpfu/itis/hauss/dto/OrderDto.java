package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.hauss.models.Account;
import ru.kpfu.itis.hauss.models.Order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

    private Long id;
    private AccountDto account;
    private OfferDto offer;
    private String date;
    private String currency;
    private Double totalPrice;
    private String comment;

    public static OrderDto from(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .account(AccountDto.from(order.getAccount()))
                .offer(OfferDto.from(order.getOffer()))
                .date(order.getDate().toString())
                .totalPrice(order.getTotalPrice())
                .currency(order.getCurrency().getName())
                .comment(order.getComment())
                .build();
    }

    public static List<OrderDto> from(List<Order> orders) {
        return orders.stream().map(OrderDto::from).collect(Collectors.toList());
    }
}
