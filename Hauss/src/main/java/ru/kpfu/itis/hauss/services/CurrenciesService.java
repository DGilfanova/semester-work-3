package ru.kpfu.itis.hauss.services;

import ru.kpfu.itis.hauss.dto.CurrencyDto;

import java.util.List;

public interface CurrenciesService {
    List<CurrencyDto> getAllCurrencies();
    CurrencyDto getById(Long id);
}
