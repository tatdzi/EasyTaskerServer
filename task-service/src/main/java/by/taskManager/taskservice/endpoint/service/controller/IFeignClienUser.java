package by.taskManager.taskservice.endpoint.service.controller;

import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.taskservice.config.property.FeignConfig;
import by.taskManager.taskservice.endpoint.service.controller.fallback.RetreiveMessageErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@FeignClient(name = "user-service")
public interface IFeignClienUser {

    @RequestMapping(value = "/users/me",method = RequestMethod.GET)
    ResponseEntity<UserDTO> saveItem(@RequestHeader("Authorization") String header);

    @RequestMapping(value = "/user-service/check",method = RequestMethod.POST)
    ResponseEntity<?> checkItems(@RequestBody Set<UUID> items);

    @RequestMapping(value = "/user-service/check/{uuid}",method = RequestMethod.POST)
    ResponseEntity<?> checkItem(@PathVariable UUID uuid);
}
