package by.taskManager.user_service.endpoints.service.controller;

import by.TaskManeger.utils.dto.MailDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name ="${natification-service.name}" ,url ="${natification-service.url}")
public interface IFeignClientNotification {
    @RequestMapping(value = "/service/verification",method = RequestMethod.POST)
    ResponseEntity<?> sendLetter(MailDetails mailDetails);
}
