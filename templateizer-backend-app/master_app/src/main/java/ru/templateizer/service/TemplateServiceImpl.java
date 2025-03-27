package ru.templateizer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.templateizer.mapper.TemplateMapper;
import ru.templateizer.model.tamplate.IncomingTemplate;
import ru.templateizer.model.tamplate.OutgoingTemplate;
import ru.templateizer.model.tamplate.Template;
import ru.templateizer.repository.TemplateRepository;

import java.time.Instant;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final TemplateMapper templateMapper;

    @Autowired
    public TemplateServiceImpl(TemplateRepository templateRepository, TemplateMapper templateMapper) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
    }

    @Override
    @Transactional
    public OutgoingTemplate addNewTemplate(IncomingTemplate template) {
        return templateMapper
                .toOutgoing(templateRepository
                        .save(toMainTemplate(template, false)));
    }

    @Override
    @Transactional
    public OutgoingTemplate addNewAdminTemplate(IncomingTemplate template) {
        return templateMapper
                .toOutgoing(templateRepository
                        .save(toMainTemplate(template, true)));
    }

    private Template toMainTemplate(IncomingTemplate template, Boolean isPublic) {
        Template mainTemplate = templateMapper.toTemplate(template, getCurrentUserId(), isPublic);
        mainTemplate.setCreatedAt(Instant.now());
        mainTemplate.setUpdatedAt(Instant.now());
        return mainTemplate;
    }

    private UUID getCurrentUserId() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return UUID.fromString(jwt.getClaimAsString("sub"));
    }
}


//переводим все в исходящий объект;
//допилим конфигурацию