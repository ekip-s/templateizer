package ru.templateizer.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.templateizer.model.tamplate.Template;


import java.util.List;
import java.util.UUID;

@Repository
public interface TemplateRepository extends JpaRepository<Template, UUID> {

    Page<Template> findAllByCategoryId(UUID categoryId, Pageable pageable);

    @Query("SELECT t FROM Template t WHERE t.userId = :userId AND (:isPublic IS NULL OR t.isPublic = :isPublic)")
    List<Template> findByUserIdWithPublicFilter(
            @Param("userId") String userId,
            @Param("isPublic") Boolean isPublic
    );
}
