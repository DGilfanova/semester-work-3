package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.hauss.dto.OfferCreationDto;
import ru.kpfu.itis.hauss.dto.OfferDto;
import ru.kpfu.itis.hauss.dto.OffersPage;
import ru.kpfu.itis.hauss.dto.StatisticsOfferDto;
import ru.kpfu.itis.hauss.exceptions.AccessException;
import ru.kpfu.itis.hauss.exceptions.AccountNotFoundException;
import ru.kpfu.itis.hauss.exceptions.CategoryNotFoundException;
import ru.kpfu.itis.hauss.exceptions.OfferNotFoundException;
import ru.kpfu.itis.hauss.helpers.constants.Constants;
import ru.kpfu.itis.hauss.models.*;
import ru.kpfu.itis.hauss.repositories.AccountsRepository;
import ru.kpfu.itis.hauss.repositories.OfferCategoriesRepository;
import ru.kpfu.itis.hauss.repositories.OffersRepository;
import ru.kpfu.itis.hauss.services.FilesService;
import ru.kpfu.itis.hauss.services.OffersService;

import java.util.List;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.DEFAULT_PAGE;

@RequiredArgsConstructor
@Service
public class OffersServiceImpl implements OffersService {

    private final AccountsRepository accountsRepository;
    private final OffersRepository offersRepository;
    private final OfferCategoriesRepository categoryRepository;

    private final FilesService filesService;

    @Override
    public OfferDto getOfferById(Long offerId) {
        return OfferDto.from(offersRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("Offer not found")));
    }

    @Override
    public OffersPage getPublicOffers(Integer page) {
        if (page == null) page = DEFAULT_PAGE;

        PageRequest pageRequest = PageRequest.of(page, Constants.DEFAULT_OFFERS_PAGE_SIZE);
        Page<Offer> servicePage = offersRepository.findAllByStatus(Status.PUBLIC, pageRequest);
        return OffersPage.builder()
                .offers(OfferDto.from(servicePage.getContent()))
                .totalPages(servicePage.getTotalPages())
                .currentPage(page)
                .build();
    }

    @Override
    public OffersPage getUserOffers(Long authUserId, Integer page) {
        if (page == null) page = DEFAULT_PAGE;

        PageRequest pageRequest = PageRequest.of(page, Constants.DEFAULT_OFFERS_PAGE_SIZE);
        Page<Offer> servicePage = offersRepository.findAllByStatusNotAndAccount_Id(
                                                                        Status.DELETED, authUserId, pageRequest);
        return OffersPage.builder()
                .offers(OfferDto.from(servicePage.getContent()))
                .totalPages(servicePage.getTotalPages())
                .currentPage(page)
                .build();
    }

    @Transactional
    @Override
    public OfferDto addUserOffer(OfferCreationDto newData, Long authUserId, MultipartFile multipartFile) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        OfferCategory category = categoryRepository.findById(newData.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found. Sorry, choose another one"));

        String photoName = null;
        if (!multipartFile.isEmpty()) {
            photoName = filesService.upload(multipartFile);
        }

        return OfferDto.from(offersRepository.save(
                Offer.builder()
                        .account(account)
                        .title(newData.getTitle())
                        .description(newData.getDescription())
                        .price(Double.valueOf(newData.getPrice()))
                        .executionTime(Integer.valueOf(newData.getExecutionTime()))
                        .category(category)
                        .photoName(photoName)
                        .status(Status.PUBLIC)
                        .build()));
    }

    @Override
    public void deleteUserOffer(Long offerId, Long authUserId) {
        Offer offer = offersRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("Offer not found"));

        checkAccess(offer, authUserId);

        offer.setStatus(Status.DELETED);

        offersRepository.save(offer);
    }

    @Transactional
    @Override
    public OfferDto editOffer(Long offerId, OfferCreationDto newData, Long authUserId) {
        Offer offer = offersRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("Offer not found"));

        checkAccess(offer, authUserId);

        OfferCategory category = categoryRepository.findById(newData.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found. Sorry, choose another one"));

        offer.setTitle(newData.getTitle());
        offer.setDescription(newData.getDescription());
        offer.setPrice(Double.valueOf(newData.getPrice()));
        offer.setCategory(category);
        offer.setExecutionTime(Integer.valueOf(newData.getExecutionTime()));

        return OfferDto.from(offersRepository.save(offer));
    }

    @Override
    public List<OfferDto> searchByCategory(Long categoryId) {
        OfferCategory category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found. Sorry, choose another one"));

        return OfferDto.from(offersRepository.findAllByStatusAndCategory(Status.PUBLIC, category));
    }

    @Override
    public List<OfferDto> searchByTitle(String title) {
        return OfferDto.from(offersRepository.findAllByStatusAndTitleIsContains(Status.PUBLIC, "%" + title + "%"));
    }

    @Override
    public List<StatisticsOfferDto> findUserMostAdvantageousOffersByCategory(Long authUserId, Long categoryId) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        List<OfferCategory> categories = categoryRepository.findAll();

        boolean contains = false;
        for (OfferCategory cat: categories) {
            if (cat.getId().equals(categoryId)) {
                contains = true;
                break;
            }
        }

        if (!contains) {
            throw new CategoryNotFoundException("Category don't exist");
        }

        return StatisticsOfferDto.from(offersRepository.findMostAdvantageousByCategoryAndUser(account.getId(), categoryId),
                offersRepository.findAveragePriceByCategory(categoryId));
    }

    @Override
    public List<StatisticsOfferDto> findMostPopularByOrdersOffers(Long authUserId) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return StatisticsOfferDto.from(offersRepository.findMostPopularByOrderAndUser(account.getId()));
    }

    private void checkAccess(Offer offer, Long authUserId) {
        if (!authUserId.equals(offer.getAccount().getId())) {
            throw new AccessException("You are not an author of this offer");
        }
    }
}
