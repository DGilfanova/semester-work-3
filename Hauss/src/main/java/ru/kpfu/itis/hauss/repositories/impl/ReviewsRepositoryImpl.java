package ru.kpfu.itis.hauss.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.hauss.exceptions.ReviewNotFoundException;
import ru.kpfu.itis.hauss.models.Review;
import ru.kpfu.itis.hauss.repositories.ReviewsRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewsRepositoryImpl implements ReviewsRepository {

    private EntityManager entityManager;

    @Autowired
    public ReviewsRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public List<Review> findAllByOfferId(Long offerId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Review> reviewCriteria = cb.createQuery(Review.class);
        Root<Review> reviewRoot = reviewCriteria.from(Review.class);
        reviewCriteria.select(reviewRoot);
        reviewCriteria.where(cb.equal(reviewRoot.get("offer").get("id"), offerId));
        return entityManager.createQuery(reviewCriteria).getResultList();
    }

    @Override
    @Transactional
    public Review save(Review review) {
        entityManager.persist(review);
        return review;
    }

    @Override
    public Optional<Review> findById(Long reviewId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Review> reviewCriteria = cb.createQuery(Review.class);
        Root<Review> reviewRoot = reviewCriteria.from(Review.class);
        reviewCriteria.select(reviewRoot);
        reviewCriteria.where(cb.equal(reviewRoot.get("id"), reviewId));
        List<Review> reviews = entityManager.createQuery(reviewCriteria).getResultList();
        return Optional.ofNullable(reviews.get(0));
    }

    @Override
    @Transactional
    public void delete(Review review) {
        entityManager.remove(review);
    }
}
