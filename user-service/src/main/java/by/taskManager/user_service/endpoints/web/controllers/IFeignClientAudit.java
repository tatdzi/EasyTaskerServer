package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.core.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${audit-service.name}",url = "${audit-service.url}")
public interface IFeignClientAudit {
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    void saveItem(UserDTO dto);

}
