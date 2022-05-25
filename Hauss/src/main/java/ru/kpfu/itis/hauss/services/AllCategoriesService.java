package ru.kpfu.itis.hauss.services;

import ru.kpfu.itis.hauss.models.IdeaCategory;
import ru.kpfu.itis.hauss.models.OfferCategory;
import ru.kpfu.itis.hauss.models.Style;

import java.util.List;

public interface AllCategoriesService {

    List<IdeaCategory> getAllIdeaCategories();
    List<OfferCategory> getAllOfferCategories();
    List<Style> getAllStyles();
}
