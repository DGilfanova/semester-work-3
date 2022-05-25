package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferCreationDto {

    @NotBlank(message = "Field shouldn't be empty")
    @Size(min = 1, max = 20, message = "Title should contain from 1 to 20 characters")
    private String title;

    @NotBlank(message = "Field shouldn't be empty")
    @Size(min = 1, max = 500, message = "Description should contain maximum 500 characters")
    private String description;

    @NotBlank(message = "Field shouldn't be empty")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Integer types only")
    @Max(value = 1000000, message = "The maximum price is 1 000 000 Р")
    @Min(value = 10, message = "The minimum price is 10 Р")
    private String price;

    @NotBlank(message = "Field shouldn't be empty")
    @Pattern(regexp="^(0|[1-9][0-9]*)$", message = "Integer types only")
    @Max(value = 1000, message = "The maximum number of days is 1000")
    @Min(value = 1, message = "The minimum number of days is 10")
    private String executionTime;

    @NotNull(message = "Field shouldn't be empty")
    private Long category;
}
