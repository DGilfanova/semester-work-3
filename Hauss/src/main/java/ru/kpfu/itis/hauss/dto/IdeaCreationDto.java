package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IdeaCreationDto {

    @NotBlank(message = "Field shouldn't be empty")
    @Size(max = 20, message = "Title should contain max 20 characters")
    private String title;

    @NotBlank(message = "Field shouldn't be empty")
    @Size(max = 1000, message = "Content should contain max 1000 characters")
    private String content;

    private Boolean isPrivate;

    @NotNull(message = "Field shouldn't be empty")
    private Long category;

    @NotNull(message = "Field shouldn't be empty")
    private Long style;
}
