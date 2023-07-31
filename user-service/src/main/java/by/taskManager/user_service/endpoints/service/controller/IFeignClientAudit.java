package by.taskManager.user_service.endpoints.service.controller;

import by.TaskManeger.utils.dto.AuditDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${audit-service.name}",url = "${audit-service.url}")
public interface IFeignClientAudit {
    @RequestMapping(value = "/audit/save",method = RequestMethod.POST)
    void saveItem(AuditDTO dto);
}
