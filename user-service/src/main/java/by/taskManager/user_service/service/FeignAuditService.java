package by.taskManager.user_service.service;

import by.TaskManeger.utils.dto.AuditDTO;
import by.taskManager.user_service.core.exception.ErrorConnectionToService;
import by.taskManager.user_service.endpoints.service.controller.IFeignClientAudit;
import by.taskManager.user_service.service.api.IAuditService;
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
        ResponseEntity<?> status = feignClientAudit.saveItem(audit);
        if (!status.getStatusCode().equals(HttpStatus.OK)){
            throw new ErrorConnectionToService("Ошибка отправки данных");
        }
    }
}
