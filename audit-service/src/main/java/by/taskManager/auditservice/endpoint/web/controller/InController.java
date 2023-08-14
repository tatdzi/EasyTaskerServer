package by.taskManager.auditservice.endpoint.web.controller;

import by.TaskManeger.utils.dto.AuditDTO;
import by.taskManager.auditservice.service.api.ISecurityService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/service")
public class InController {
    private ISecurityService service;

    public InController(ISecurityService service) {
        this.service = service;
    }

    @PermitAll
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody AuditDTO audit){
        service.save(audit);
    }
}
