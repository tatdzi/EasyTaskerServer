package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.core.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "audit-service",url = "http://audit-service:8080")
public interface IFeignClientNatification {
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    void sendLetter(UserDTO dto);
}
