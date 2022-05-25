package ru.kpfu.itis.hauss.dto;

import lombok.Builder;
import lombok.Data;
import ru.kpfu.itis.hauss.models.Offer;
import ru.kpfu.itis.hauss.models.OfferCategory;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class StatisticsOfferDto {

    private Long id;
    private String title;
    private Integer price;
    private Integer executionTime;
    private OfferCategory category;
    private Integer ordersCount;
    private Double averagePrice;

    public static StatisticsOfferDto from(Offer offer) {

        return StatisticsOfferDto.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .price(offer.getPrice().intValue())
                .executionTime(offer.getExecutionTime())
                .category(offer.getCategory())
                .ordersCount(offer.getOrders().size())
                .build();
    }

    public static List<StatisticsOfferDto> from(List<Offer> offers) {
        return offers.stream().map(StatisticsOfferDto::from).collect(Collectors.toList());
    }

    public static List<StatisticsOfferDto> from(List<Offer> offers, Double averagePrice) {
        return offers
                .stream()
                .map((e) ->
                                        {StatisticsOfferDto s = StatisticsOfferDto.from(e);
                                            s.setAveragePrice(averagePrice);
                                        return s;})
                .collect(Collectors.toList());
    }
}
