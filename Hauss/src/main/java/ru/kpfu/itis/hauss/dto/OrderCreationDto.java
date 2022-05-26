package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

import static ru.kpfu.itis.hauss.helpers.constants.Constants.DEFAULT_CURRENCY;
import static ru.kpfu.itis.hauss.helpers.constants.Constants.ORDER_DATE_TEMPLATE;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreationDto {

    @NotNull(message = "Field shouldn't be empty")
    private Long offerId;

    @NotNull(message = "Field shouldn't be empty")
    private LocalDate date;

    @NotNull(message = "Field shouldn't be empty")
    private Double totalPrice;

    @NotNull(message = "Field shouldn't be empty")
    private Long currencyId;
    private String currencyName;

    @NotBlank(message = "Field shouldn't be empty")
    @Size(max = 500, message = "Comment should contain no more than 500 characters")
    private String comment;
}

