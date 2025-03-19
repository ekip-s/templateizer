package ru.templateizer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.templateizer.model.category.Category;
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
    @GetMapping
    public void getCategoryById(UUID id) {

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
    public void addCategory() {

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
    @DeleteMapping
    public void deleteCategoryById(UUID id) {

    }
}
