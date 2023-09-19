package by.taskManager.reportservice.endpoint.service.controller;


import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.ReportParamAudit;
import jakarta.annotation.security.PermitAll;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "audit-service")
public interface IFeignClientAudit {
    @RequestMapping(value = "/service/save",method = RequestMethod.POST)
    ResponseEntity<?> saveItem(AuditDTO audit);
    @PermitAll
    @RequestMapping(value ="/service/get",method = RequestMethod.POST)
    ResponseEntity<List<AuditDTO>> getList(@RequestBody ReportParamAudit paramAudit);
}
