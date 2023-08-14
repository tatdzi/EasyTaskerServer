package by.taskManager.taskservice.service;

import by.TaskManeger.utils.dto.AuditDTO;

import by.taskManager.taskservice.core.exception.ErrorConnectionToService;
import by.taskManager.taskservice.endpoint.service.controller.IFeignClientAudit;
import by.taskManager.taskservice.service.api.IAuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeignAuditService implements IAuditService {
    private IFeignClientAudit feignClientAudit;

    public FeignAuditService(IFeignClientAudit feignClientAudit) {
        this.feignClientAudit = feignClientAudit;
    }

    @Override
    public void saveItem(AuditDTO audit) {
       feignClientAudit.saveItem(audit);
    }
}
