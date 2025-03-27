package ru.templateizer.service;

import ru.templateizer.model.category.CategoryDTO;

import java.util.List;
import java.util.UUID;

public interface CategoriesService {
    List<CategoryDTO> getCategories();
    CategoryDTO addCategory(CategoryDTO category);
    void deleteCategoryById(UUID categoryId);
    CategoryDTO getCategoryById(UUID categoryId);
}
