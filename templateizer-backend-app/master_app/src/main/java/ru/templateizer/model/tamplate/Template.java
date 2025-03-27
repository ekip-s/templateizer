package ru.templateizer.model.tamplate;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import ru.templateizer.model.UserTemplateMapping;
import ru.templateizer.model.category.Category;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "templates")
public class Template {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(name = "is_public", nullable = false)
    private Boolean isPublic = false;
    @Column(name = "user_id")
    private UUID userId;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;
    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserTemplateMapping> userMappings = new HashSet<>();

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", category=" + category +
                ", isPublic=" + isPublic +
                ", userId='" + userId + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
