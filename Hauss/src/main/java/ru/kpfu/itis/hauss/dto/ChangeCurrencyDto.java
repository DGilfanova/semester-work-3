package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeCurrencyDto {

    @NotNull(message = "Field shouldn't be empty")
    private Long currencyId;

    @NotNull(message = "Field shouldn't be empty")
    private Long newCurrencyId;

    @NotNull(message = "Field shouldn't be empty")
    private Double totalPrice;
}
