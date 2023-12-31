package by.taskManager.taskservice.endpoint.service.controller;

import by.TaskManeger.utils.dto.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "audit-service")
public interface IFeignClientAudit {
    @RequestMapping(value = "/service/save",method = RequestMethod.POST)
    ResponseEntity<?> saveItem(AuditDTO audit);
}
