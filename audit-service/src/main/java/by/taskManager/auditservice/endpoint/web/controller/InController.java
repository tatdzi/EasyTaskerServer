package by.taskManager.auditservice.endpoint.web.controller;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.ReportParamAudit;
import by.taskManager.auditservice.dao.entity.AuditEntity;
import by.taskManager.auditservice.service.api.ISecurityService;
import jakarta.annotation.security.PermitAll;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/service")
public class InController {
    private ISecurityService service;
    private ConversionService conversionService;

    public InController(ISecurityService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @PermitAll
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void save(@RequestBody AuditDTO audit){
        service.save(audit);
    }
    @PermitAll
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public List<AuditDTO> get(@RequestBody ReportParamAudit paramAudit){
        List<AuditDTO> dtos = new ArrayList<>();
        for (AuditEntity entity:service.getList(paramAudit)){
            dtos.add(conversionService.convert(entity,AuditDTO.class));
        }
        return dtos;
    }
}
