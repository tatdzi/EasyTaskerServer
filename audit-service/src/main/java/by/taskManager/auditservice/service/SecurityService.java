package by.taskManager.auditservice.service;

import by.taskManager.auditservice.core.dto.AuditDTO;
import by.taskManager.auditservice.core.dto.PageDTO;
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

    public PageDTO getPage(Integer page,Integer size){
        Page<AuditEntity> pageResponse =auditData.findAll(PageRequest.of(page, size));
        List<AuditDTO> auditDTOS = new ArrayList<>();
        for (AuditEntity entity:pageResponse.getContent()){
            auditDTOS.add(new AuditDTO(entity));
        }
        return new PageDTO<>(pageResponse,auditDTOS);
    }
    public AuditEntity getCard(UUID uuid){
        return auditData.findById(uuid).
                orElseThrow(()-> new NotCorrectUUIDException("Пользователь с таким uuid не найден"));
    }


}
