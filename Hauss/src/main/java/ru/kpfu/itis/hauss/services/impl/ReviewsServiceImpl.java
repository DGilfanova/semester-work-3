package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.hauss.dto.*;
import ru.kpfu.itis.hauss.exceptions.AccessException;
import ru.kpfu.itis.hauss.exceptions.AccountNotFoundException;
import ru.kpfu.itis.hauss.exceptions.OfferNotFoundException;
import ru.kpfu.itis.hauss.exceptions.ReviewNotFoundException;
import ru.kpfu.itis.hauss.models.Account;
import ru.kpfu.itis.hauss.models.Offer;
import ru.kpfu.itis.hauss.models.Review;
import ru.kpfu.itis.hauss.repositories.AccountsRepository;
import ru.kpfu.itis.hauss.repositories.OffersRepository;
import ru.kpfu.itis.hauss.repositories.ReviewsRepository;
import ru.kpfu.itis.hauss.services.ReviewsService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final AccountsRepository accountsRepository;
    private final OffersRepository offersRepository;

    @Override
    public List<ReviewDto> getReviewsByOffer(Long offerId) {
        return ReviewDto.from(reviewsRepository.findAllByOfferId(offerId));
    }

    @Transactional
    @Override
    public ReviewDto addReview(ReviewCreationDto newData, Long authUserId, Long offerId) {
        Account account = accountsRepository.findById(authUserId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        Offer offer = offersRepository.findById(offerId)
                .orElseThrow(() -> new OfferNotFoundException("Offer not found"));

        return ReviewDto.from(reviewsRepository.save(
                Review.builder()
                        .author(account)
                        .offer(offer)
                        .content(newData.getContent())
                        .build()
        ));
    }

    @Transactional
    @Override
    public ReviewDto updateReview(ReviewCreationDto newData, Long reviewId, Long authUserId) {
        Review review = reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        checkAccess(review, authUserId);

        review.setContent(newData.getContent());

        return ReviewDto.from(reviewsRepository.save(review));
    }

    @Override
    public void deleteReview(Long reviewId, Long authUserId) {
        Review review = reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found"));

        checkAccess(review, authUserId);

        reviewsRepository.delete(review);
    }

    private void checkAccess(Review review, Long authUserId) {
        if (!authUserId.equals(review.getAuthor().getId())) {
            throw new AccessException("You are not an author of this offer");
        }
    }
}
