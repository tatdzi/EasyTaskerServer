package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.core.dto.MailDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name ="${natification-service.name}" ,url ="${natification-service.url}")
public interface IFeignClientNatification {
    @RequestMapping(value = "/service/verification",method = RequestMethod.POST)
    HttpStatus sendLetter(MailDetails mailDetails);
}
