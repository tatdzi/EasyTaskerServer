package by.taskManager.auditservice.endpoint.service.controller;

import by.TaskManeger.utils.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@FeignClient(name = "${user-service.name}",url = "${user-service.url}")
public interface IFeignClienUser {
    @RequestMapping(value = "/users/me",method = RequestMethod.GET)
    ResponseEntity<UserDTO> saveItem(@RequestHeader("Authorization") String header);

}
