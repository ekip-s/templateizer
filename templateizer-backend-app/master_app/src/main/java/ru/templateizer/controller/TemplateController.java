package ru.templateizer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.templateizer.model.tamplate.IncomingTemplate;
import ru.templateizer.model.tamplate.OutgoingTemplate;
import ru.templateizer.service.TemplateService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/template/api/v1")
@Tag(name="template_controller", description = "Управление шаблонами")
public class TemplateController {

    private final TemplateService templateService;

    @Operation(
            summary = "Создать шаблон"
    )
    @PostMapping
    public OutgoingTemplate addNewTemplate(@RequestBody IncomingTemplate template) {
        log.info("POST: TemplateController addNewTemplate, параметры: {}", template);
        return templateService.addNewTemplate(template);
    }

    @Operation(
            summary = "Создать общий шаблон",
            description = "Метод, для создания администратором общего шаблона"
    )
    @PostMapping("/admin")
    @Secured("ROLE_templateizer_admin")
    public OutgoingTemplate addNewAdminTemplate(@RequestBody IncomingTemplate template) {
        log.info("POST: TemplateController addNewAdminTemplate, параметры: {}", template);
        return templateService.addNewAdminTemplate(template);
    }
}
