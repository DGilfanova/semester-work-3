package ru.kpfu.itis.hauss.repositories;

import ru.kpfu.itis.hauss.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewsRepository {
    List<Review> findAllByOfferId(Long offerId);
    Review save(Review review);
    Optional<Review> findById(Long reviewId);
    void delete(Review review);
}
