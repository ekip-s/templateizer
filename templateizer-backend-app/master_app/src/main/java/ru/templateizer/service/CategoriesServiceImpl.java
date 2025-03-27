package ru.templateizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.templateizer.exception.ConflictException;
import ru.templateizer.exception.NotFoundException;
import ru.templateizer.mapper.CategoryMapper;
import ru.templateizer.model.category.Category;
import ru.templateizer.model.category.CategoryDTO;
import ru.templateizer.repository.CategoryRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

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
        return categoryMapper.toDTOList(categoryRepository.findAllByOrderByCreatedAtDesc());
    }

    @Override
    @Transactional
    public CategoryDTO addCategory(CategoryDTO categoryDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Category category = categoryMapper.toCategory(categoryDto);
        category.setCreatedAt(Instant.now());
        category.setCreator(authentication.getName());

        return categoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    @Transactional
    public void deleteCategoryById(UUID categoryId) {
        CategoryDTO category = getCategoryById(categoryId);
        if(category.getImmutable()) {
            throw new ConflictException("Категория неизменяемая", "Конфликт");
        } else {
            categoryRepository.deleteById(categoryId);
        }
    }

    @Override
    public CategoryDTO getCategoryById(UUID categoryId) {
        return categoryMapper
                .toDTO(categoryRepository
                        .findById(categoryId)
                        .orElseThrow(() -> new NotFoundException("Нет категории с указанным id", "Нет данных")));
    }
}
