package by.taskManager.notificationservice.endpoint.web.controller;

import by.TaskManeger.utils.dto.MailDetails;
import by.taskManager.notificationservice.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
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
