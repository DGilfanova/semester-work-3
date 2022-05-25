package ru.kpfu.itis.hauss.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.hauss.models.IdeaCategory;
import ru.kpfu.itis.hauss.models.OfferCategory;
import ru.kpfu.itis.hauss.models.Style;
import ru.kpfu.itis.hauss.repositories.IdeaCategoriesRepository;
import ru.kpfu.itis.hauss.repositories.OfferCategoriesRepository;
import ru.kpfu.itis.hauss.repositories.StyleRepository;
import ru.kpfu.itis.hauss.services.AllCategoriesService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AllCategoriesServiceImpl implements AllCategoriesService {

    private final IdeaCategoriesRepository ideaCategoriesRepository;
    private final OfferCategoriesRepository offerCategoriesRepository;
    private final StyleRepository styleRepository;

    @Override
    public List<IdeaCategory> getAllIdeaCategories() {
        return ideaCategoriesRepository.findAll();
    }

    @Override
    public List<OfferCategory> getAllOfferCategories() {
        return offerCategoriesRepository.findAll();
    }

    @Override
    public List<Style> getAllStyles() {
        return styleRepository.findAll();
    }
}
