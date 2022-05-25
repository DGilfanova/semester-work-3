package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.hauss.models.Style;

import java.util.List;

public interface StyleRepository extends CrudRepository<Style, Long> {
    List<Style> findAll();
}
