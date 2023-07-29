package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.core.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(url = "http://audit-service:8080")
public interface IFeignClientAudit {
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    ResponseEntity<?> saveItem(UserDTO dto, String text);

}
