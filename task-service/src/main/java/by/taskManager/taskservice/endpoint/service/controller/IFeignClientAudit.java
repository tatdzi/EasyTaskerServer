package by.taskManager.taskservice.endpoint.service.controller;

import by.TaskManeger.utils.dto.AuditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${audit-service.name}",url = "${audit-service.url}")
public interface IFeignClientAudit {
    @RequestMapping(value = "/service/save",method = RequestMethod.POST)
    HttpStatus saveItem(AuditDTO audit);
}
