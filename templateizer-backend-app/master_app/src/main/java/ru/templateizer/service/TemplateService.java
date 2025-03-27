package ru.templateizer.service;

import ru.templateizer.model.tamplate.IncomingTemplate;
import ru.templateizer.model.tamplate.OutgoingTemplate;

public interface TemplateService {
    OutgoingTemplate addNewTemplate(IncomingTemplate template);
    OutgoingTemplate addNewAdminTemplate(IncomingTemplate template);
}
