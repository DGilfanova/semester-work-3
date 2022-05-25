package ru.kpfu.itis.hauss.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.hauss.dto.ApiResponse;
import ru.kpfu.itis.hauss.dto.ErrorDto;
import ru.kpfu.itis.hauss.dto.ReviewCreationDto;
import ru.kpfu.itis.hauss.dto.ReviewDto;
import ru.kpfu.itis.hauss.exceptions.ReviewNotFoundException;
import ru.kpfu.itis.hauss.security.details.AccountUserDetails;
import ru.kpfu.itis.hauss.services.ReviewsService;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReviewsRestController {

    private final ReviewsService reviewsService;

    @GetMapping("/offers/{offer-id}/reviews")
    public ResponseEntity<ApiResponse> getOfferReviews(@PathVariable("offer-id") Long offerId) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .success(true)
                        .data(reviewsService.getReviewsByOffer(offerId))
                        .build());
    }

    @PostMapping("/offers/{offer-id}/reviews")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse> addReview(@PathVariable("offer-id") Long offerId,
                                               @Valid @RequestBody ReviewCreationDto reviewCreationDto,
                                               @AuthenticationPrincipal AccountUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.builder()
                        .success(true)
                        .data(reviewsService.addReview(reviewCreationDto, userDetails.getAccount().getId(), offerId))
                        .build());
    }

    @PatchMapping("/offers/*/reviews/{review-id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse> updateReview(@PathVariable("review-id") Long reviewId,
                                                  @Valid @RequestBody ReviewCreationDto reviewCreationDto,
                                                  @AuthenticationPrincipal AccountUserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse.builder()
                        .success(true)
                        .data(reviewsService.updateReview(reviewCreationDto, reviewId, userDetails.getAccount().getId()))
                        .build());
    }

    @DeleteMapping("/offers/*/reviews/{review-id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse> deleteReview(@PathVariable("review-id") Long reviewId,
                                                   @AuthenticationPrincipal AccountUserDetails userDetails) {
        reviewsService.deleteReview(reviewId, userDetails.getAccount().getId());
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(ApiResponse.builder().success(true).build());
    }
}

