package ru.templateizer.model.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;
import ru.templateizer.model.tamplate.Template;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;
    @Column(name = "creator")
    private String creator;
    @Column(name = "immutable")
    private Boolean immutable;
    @OneToMany(mappedBy = "category")
    private Set<Template> templates = new HashSet<>();

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", creator='" + creator + '\'' +
                ", immutable=" + immutable +
                '}';
    }
}
