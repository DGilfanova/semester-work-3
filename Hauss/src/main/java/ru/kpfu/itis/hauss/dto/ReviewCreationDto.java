package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewCreationDto {

    @NotBlank(message = "Field shouldn't be empty")
    @Size(min = 2, message = "Minimum size is 2")
    String content;
}
