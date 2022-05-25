package ru.kpfu.itis.hauss.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.itis.hauss.models.IdeaCategory;

import java.util.List;

public interface IdeaCategoriesRepository extends CrudRepository<IdeaCategory, Long> {
    List<IdeaCategory> findAll();
}
