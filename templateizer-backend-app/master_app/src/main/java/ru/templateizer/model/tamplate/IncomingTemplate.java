package ru.templateizer.model.tamplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IncomingTemplate {

    private String name;
    private String content;
    private UUID categoryId;

    @Override
    public String toString() {
        return "IncomingTemplate{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}
