package by.taskManager.notificationservice.endpoint.web.controller;

import by.taskManager.notificationservice.core.dto.MailDetails;
import by.taskManager.notificationservice.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/service")
public class MailController {
    private MailService service;

    public MailController(MailService service) {
        this.service = service;
    }

    @RequestMapping(value = "/verification",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void verification(@RequestBody MailDetails mailDetails){
        service.sendVerificationLetter(mailDetails);
    }
}
