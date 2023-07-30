package by.taskManager.auditservice.endpoint.service.controller;

import by.TaskManeger.utils.dto.AuditDTO;
import by.taskManager.auditservice.service.api.ISecurityService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class InController {
    private ISecurityService service;

    public InController(ISecurityService service) {
        this.service = service;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public void save(@RequestBody AuditDTO audit){
        service.save(audit);
    }
}
