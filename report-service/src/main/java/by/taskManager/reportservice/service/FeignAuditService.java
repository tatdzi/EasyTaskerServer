package by.taskManager.reportservice.service;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.ReportParamAudit;
import by.taskManager.reportservice.endpoint.service.controller.IFeignClientAudit;
import by.taskManager.reportservice.service.api.IAuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FeignAuditService implements IAuditService {
    private IFeignClientAudit feignClientAudit;

    public FeignAuditService(IFeignClientAudit feignClientAudit) {
        this.feignClientAudit = feignClientAudit;
    }

    @Override
    public List<AuditDTO> getList(ReportParamAudit paramAudit) {
        ResponseEntity<List<AuditDTO>> response = feignClientAudit.getList(paramAudit);
        return response.getBody();
    }
}
