package ru.templateizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.templateizer.model.tamplate.Template;
import ru.templateizer.model.UserTemplateMapping;
import ru.templateizer.model.UserTemplateMappingId;

import java.util.List;

@Repository
public interface UserTemplateMappingRepository extends JpaRepository<UserTemplateMapping, UserTemplateMappingId> {

    List<UserTemplateMapping> findByUserId(String userId);

    @Query("SELECT u.template FROM UserTemplateMapping u WHERE u.userId = :userId AND u.isFavorite = true")
    List<Template> findFavoriteTemplates(@Param("userId") String userId);
}