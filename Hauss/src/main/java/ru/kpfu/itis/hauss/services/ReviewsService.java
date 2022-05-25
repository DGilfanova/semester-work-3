package ru.kpfu.itis.hauss.services;

import ru.kpfu.itis.hauss.dto.ReviewCreationDto;
import ru.kpfu.itis.hauss.dto.ReviewDto;

import java.util.List;

public interface ReviewsService {
    List<ReviewDto> getReviewsByOffer(Long offerId);
    ReviewDto addReview(ReviewCreationDto newData, Long authUserId, Long offerId);
    ReviewDto updateReview(ReviewCreationDto newData, Long reviewId, Long authUserId);
    void deleteReview(Long reviewId, Long authUserId);
}
