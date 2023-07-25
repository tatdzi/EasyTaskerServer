package by.taskManager.auditservice.endpoint.web.controller;

import by.taskManager.auditservice.core.dto.PageDTO;
import by.taskManager.auditservice.service.SecurityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
