package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.hauss.models.Review;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

    private Long id;
    private String authorPhotoLink;
    private String authorFirstName;
    private String authorLastName;
    private String content;

    public static ReviewDto from(Review review) {
        AccountDto accountDto = AccountDto.from(review.getAuthor());
        return ReviewDto.builder()
                .id(review.getId())
                .authorPhotoLink(accountDto.getPhotoLink())
                .authorFirstName(accountDto.getFirstName())
                .authorLastName(accountDto.getLastName())
                .content(review.getContent())
                .build();
    }

    public static List<ReviewDto> from(List<Review> reviews) {
        return reviews.stream().map(ReviewDto::from).collect(Collectors.toList());
    }
}
