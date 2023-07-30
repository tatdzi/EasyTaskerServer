package by.taskManager.auditservice.service;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.auditservice.core.exception.NotCorrectUUIDException;
import by.taskManager.auditservice.dao.api.IAuditData;
import by.taskManager.auditservice.dao.entity.AuditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SecurityService {
    private IAuditData auditData;

    public SecurityService(IAuditData auditData) {
        this.auditData = auditData;
    }

    public PageDTO getPage(Integer page, Integer size){
        Page<AuditEntity> pageResponse =auditData.findAll(PageRequest.of(page, size));
        List<AuditDTO> auditDTOS = new ArrayList<>();
        for (AuditEntity audit:pageResponse.getContent()){
            auditDTOS.add(new AuditDTO(audit.getUuid(),
                    audit.getDt_create(),
                    audit.getUser(),
                    audit.getText(),
                    audit.getType(),
                    audit.getId()));
        }
        return new PageDTO<>(pageResponse,auditDTOS);
    }
    public AuditDTO getCard(UUID uuid){
        AuditEntity audit = auditData.findById(uuid).
                orElseThrow(()-> new NotCorrectUUIDException("Пользователь с таким uuid не найден"));
        AuditDTO dto = new AuditDTO(audit.getUuid(),
                audit.getDt_create(),
                audit.getUser(),
                audit.getText(),
                audit.getType(),
                audit.getId());
        return dto;
    }


}
