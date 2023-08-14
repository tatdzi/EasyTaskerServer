package by.taskManager.auditservice.endpoint.web.controller;


import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.auditservice.service.api.ISecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class SecurityController {
    private ISecurityService service;

    public SecurityController(ISecurityService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageDTO getPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                           @RequestParam(name = "size", defaultValue = "20") Integer size){
        return service.getPage(page,size) ;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{uuid}",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public AuditDTO getCard(@PathVariable UUID uuid){
        return service.getCard(uuid);
    }
}
