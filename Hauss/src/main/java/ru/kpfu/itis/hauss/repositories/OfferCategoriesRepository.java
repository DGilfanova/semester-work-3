package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.hauss.models.OfferCategory;

import java.util.List;

public interface OfferCategoriesRepository extends CrudRepository<OfferCategory, Long> {
    List<OfferCategory> findAll();
}
