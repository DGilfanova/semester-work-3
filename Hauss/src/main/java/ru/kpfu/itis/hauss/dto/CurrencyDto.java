package ru.kpfu.itis.hauss.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.kpfu.itis.hauss.models.Currency;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrencyDto {

    private Long id;
    private String name;

    public static CurrencyDto from(Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .name(currency.getName())
                .build();
    }

    public static List<CurrencyDto> from(List<Currency> currencies) {
        return currencies.stream().map(CurrencyDto::from).collect(Collectors.toList());
    }
}
