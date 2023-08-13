package by.taskManager.auditservice.service;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.PageDTO;
import by.TaskManeger.utils.dto.TokenDTO;
import by.taskManager.auditservice.core.exception.NotCorrectUUIDException;
import by.taskManager.auditservice.dao.api.IAuditData;
import by.taskManager.auditservice.dao.entity.AuditEntity;
import by.taskManager.auditservice.service.api.ISecurityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SecurityService implements ISecurityService {
    private IAuditData auditData;

    public SecurityService(IAuditData auditData) {
        this.auditData = auditData;
    }
    @Override
    @Transactional
    public void save(AuditDTO audit){
        auditData.saveAndFlush(new AuditEntity(audit));
    }
    @Override
    @Transactional(readOnly = true)
    public PageDTO getPage(Integer page, Integer size){
        Page<AuditEntity> pageResponse =auditData.findAll(PageRequest.of(page, size));
        List<AuditDTO> auditDTOS = new ArrayList<>();
        for (AuditEntity audit:pageResponse.getContent()){
            TokenDTO dto = new TokenDTO(audit.getUserUuid(),audit.getUserMail(),audit.getUserFio(),audit.getUserRole());
            auditDTOS.add(new AuditDTO(audit.getUuid(),
                    audit.getDtCreate(),
                    dto,
                    audit.getText(),
                    audit.getType(),
                    audit.getId()));
        }
        return new PageDTO<>(pageResponse,auditDTOS);
    }
    @Override
    @Transactional(readOnly = true)
    public AuditDTO getCard(UUID uuid){
        AuditEntity audit = auditData.findById(uuid).
                orElseThrow(()-> new NotCorrectUUIDException("Пользователь с таким uuid не найден"));
        TokenDTO dto1 = new TokenDTO(audit.getUserUuid(),audit.getUserMail(),audit.getUserFio(),audit.getUserRole());
        AuditDTO dto = new AuditDTO(audit.getUuid(),
                audit.getDtCreate(),
                dto1,
                audit.getText(),
                audit.getType(),
                audit.getId());
        return dto;
    }


}
