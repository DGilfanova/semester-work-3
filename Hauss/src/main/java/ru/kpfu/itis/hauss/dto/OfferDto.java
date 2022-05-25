package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.hauss.models.Offer;
import ru.kpfu.itis.hauss.models.OfferCategory;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.PHOTO_DIRECTORY;
import static ru.kpfu.itis.hauss.helpers.processors.UploadFilesLinkProcessor.getLinkByPhotoName;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {

    private Long id;
    private AccountDto account;
    private String title;
    private String description;
    private Integer price;
    private Integer executionTime;
    private OfferCategory category;
    private String photoLink;

    public static OfferDto from(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .account(AccountDto.from(offer.getAccount()))
                .title(offer.getTitle())
                .description(offer.getDescription())
                .price(offer.getPrice().intValue())
                .executionTime(offer.getExecutionTime())
                .category(offer.getCategory())
                .photoLink(getLinkByPhotoName(offer.getPhotoName()))
                .build();
    }

    public static List<OfferDto> from(List<Offer> offers) {
        return offers.stream().map(OfferDto::from).collect(Collectors.toList());
    }
}

