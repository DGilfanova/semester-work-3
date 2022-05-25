package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.hauss.models.Currency;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<Currency, Long> {
    Optional<Currency> findById(Long id);
    Optional<Currency> findByName(String name);
    List<Currency> findAll();
}
