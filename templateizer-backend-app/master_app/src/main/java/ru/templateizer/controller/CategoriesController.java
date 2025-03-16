package ru.templateizer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/categories/api/v1")
@Tag(name="categories_controller", description = "Управление категориями")
public class CategoriesController {

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
    public void getCategories() {

    }

    @Operation(
            summary = "Все категории"
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
