package ru.templateizer.model.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private UUID id;
    private String name;
    private Instant createdAt;
    private String creator;
    private Boolean immutable;

    @Override
    public String toString() {
        return "CategoryDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", creator='" + creator + '\'' +
                ", immutable=" + immutable +
                '}';
    }
}
