package ru.templateizer.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.templateizer.mapper.CategoryMapper;
import ru.templateizer.model.category.CategoryDTO;
import ru.templateizer.repository.CategoryRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoriesServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryMapper.toDTOList(categoryRepository.findAll());
    }
}
