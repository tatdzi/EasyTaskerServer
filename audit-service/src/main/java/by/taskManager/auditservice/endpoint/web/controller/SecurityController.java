package by.taskManager.auditservice.endpoint.web.controller;


import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.auditservice.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/audit")
public class SecurityController {
    private SecurityService service;

    public SecurityController(SecurityService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO> getPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                           @RequestParam(name = "size", defaultValue = "20") Integer size){
        return new ResponseEntity<>(service.getPage(page,size), HttpStatus.OK) ;
    }
    @RequestMapping(value = "/{uuid}",method = RequestMethod.GET)
    public ResponseEntity<AuditDTO> getCard(@PathVariable UUID uuid){
        return new ResponseEntity<>(service.getCard(uuid),HttpStatus.OK);
    }
}
