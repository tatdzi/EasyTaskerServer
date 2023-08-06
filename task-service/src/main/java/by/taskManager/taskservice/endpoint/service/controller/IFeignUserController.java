package by.taskManager.taskservice.endpoint.service.controller;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${user-service.name}",url = "${user-service.url}")
public interface IFeignUserController {
    @RequestMapping(value = "/users/me",method = RequestMethod.GET)
    ResponseEntity<UserDTO> saveItem(@RequestHeader("Authorization") String header);

}
