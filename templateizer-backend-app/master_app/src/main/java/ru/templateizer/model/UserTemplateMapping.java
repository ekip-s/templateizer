package ru.templateizer.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UpdateTimestamp;
import ru.templateizer.model.tamplate.Template;

import java.time.Instant;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserTemplateMappingId.class)
@Entity
@Table(name = "user_template_mapping")
public class UserTemplateMapping {

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Id
    @Column(name = "template_id", nullable = false) // Теперь это UUID, а не Template
    private UUID templateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "template_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Template template;

    @Column(name = "is_favorite", nullable = false)
    private Boolean isFavorite = false;

    @Column(columnDefinition = "SMALLINT CHECK (rating BETWEEN 1 AND 5)")
    private Integer rating;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
}

