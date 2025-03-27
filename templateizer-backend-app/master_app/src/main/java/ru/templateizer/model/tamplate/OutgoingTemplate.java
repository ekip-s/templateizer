package ru.templateizer.model.tamplate;

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
public class OutgoingTemplate {
    private UUID id;
    private String name;
    private String content;
    private UUID categoryId;
    private Boolean isPublic;
    private Instant createdAt;
}
