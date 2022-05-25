package ru.kpfu.itis.hauss.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.hauss.dto.OfferCreationDto;
import ru.kpfu.itis.hauss.dto.OfferDto;
import ru.kpfu.itis.hauss.dto.OffersPage;
import ru.kpfu.itis.hauss.dto.StatisticsOfferDto;

import java.util.List;

public interface OffersService {
    OfferDto getOfferById(Long offerId);
    OffersPage getPublicOffers(Integer page);
    OffersPage getUserOffers(Long authUserId, Integer page);
    OfferDto addUserOffer(OfferCreationDto newData, Long authUserId, MultipartFile multipartFile);
    void deleteUserOffer(Long offerId, Long authUserId);
    OfferDto editOffer(Long offerId, OfferCreationDto newData, Long authUserId);
    List<OfferDto> searchByCategory(Long categoryId);
    List<OfferDto> searchByTitle(String title);
    List<StatisticsOfferDto> findUserMostAdvantageousOffersByCategory(Long authUserId, Long categoryId);
    List<StatisticsOfferDto> findMostPopularByOrdersOffers(Long authUserId);
}

