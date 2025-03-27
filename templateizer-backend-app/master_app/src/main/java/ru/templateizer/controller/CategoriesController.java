package ru.templateizer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.templateizer.model.category.CategoryDTO;
import ru.templateizer.service.CategoriesService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories/api/v1")
@Tag(name="categories_controller", description = "Управление категориями")
public class CategoriesController {

    private final CategoriesService categoriesService;

    @Operation(
            summary = "Получить категорию по id"
    )
    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(UUID categoryId) {
        log.info("GET: CategoriesController getCategoryById, id: {}", categoryId);
        return categoriesService.getCategoryById(categoryId);
    }

    @Operation(
            summary = "Все категории"
    )
    @GetMapping("/list")
    public List<CategoryDTO> getCategories() {
        log.info("GET: CategoriesController getCategories");
        return categoriesService.getCategories();
    }

    @Operation(
            summary = "Добавить категорию"
    )
    @PostMapping
    @Secured("ROLE_templateizer_admin")
    public CategoryDTO addCategory(@RequestBody CategoryDTO category) {
        log.info("POST: CategoriesController addCategory, параметры: {}", category);
        return categoriesService.addCategory(category);
    }

    @Operation(
            summary = "Обновление названия категории"
    )
    @PatchMapping
    public void setCategoryName() {

    }

    @Operation(
            summary = "Удаление категории по id"
    )
    @DeleteMapping("/{categoryId}")
    @Secured("ROLE_templateizer_admin")
    public void deleteCategoryById(@PathVariable UUID categoryId) {
        log.info("DELETE: CategoriesController deleteCategoryById, id: {}", categoryId);
        categoriesService.deleteCategoryById(categoryId);
    }
}
