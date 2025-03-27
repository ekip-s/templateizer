package ru.templateizer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.templateizer.model.category.Category;
import ru.templateizer.model.tamplate.IncomingTemplate;
import ru.templateizer.model.tamplate.OutgoingTemplate;
import ru.templateizer.model.tamplate.Template;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TemplateMapper {
    TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", source = "incomingTemplate.categoryId", qualifiedByName = "mapCategoryIdToCategory")
    @Mapping(target = "isPublic", source = "isPublic")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "userMappings", ignore = true)
    @Mapping(target = "userId", source = "userId")
    Template toTemplate(IncomingTemplate incomingTemplate, UUID userId, Boolean isPublic);

    @Mapping(source = "category.id", target = "categoryId")
    OutgoingTemplate toOutgoing(Template template);

    @Named("mapCategoryIdToCategory")
    default Category mapCategoryIdToCategory(UUID categoryId) {
        if (categoryId == null) {
            return null;
        }
        Category category = new Category();
        category.setId(categoryId);
        return category;
    }
}
