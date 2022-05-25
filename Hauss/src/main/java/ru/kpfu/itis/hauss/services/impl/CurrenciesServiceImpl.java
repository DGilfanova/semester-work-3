package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hauss.dto.CurrencyDto;
import ru.kpfu.itis.hauss.exceptions.CurrencyNotFoundException;
import ru.kpfu.itis.hauss.repositories.CurrencyRepository;
import ru.kpfu.itis.hauss.services.CurrenciesService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CurrenciesServiceImpl implements CurrenciesService {

    private final CurrencyRepository currencyRepository;

    @Override
    public List<CurrencyDto> getAllCurrencies() {
        return CurrencyDto.from(currencyRepository.findAll());
    }

    @Override
    public CurrencyDto getById(Long id) {
        return CurrencyDto.from(currencyRepository.findById(id)
                .orElseThrow(() -> new CurrencyNotFoundException("Currency not found")));
    }
}
