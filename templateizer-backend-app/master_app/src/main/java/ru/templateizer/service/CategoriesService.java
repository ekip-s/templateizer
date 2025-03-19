package ru.templateizer.service;

import ru.templateizer.model.category.Category;
import ru.templateizer.model.category.CategoryDTO;

import java.util.List;

public interface CategoriesService {
    List<CategoryDTO> getCategories();
}
