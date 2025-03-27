package ru.templateizer.mapper;

import org.mapstruct.Mapper;
import ru.templateizer.model.category.Category;
import ru.templateizer.model.category.CategoryDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO toDTO(Category category);
    List<CategoryDTO> toDTOList(List<Category> categories);
    Category toCategory(CategoryDTO category);
}
