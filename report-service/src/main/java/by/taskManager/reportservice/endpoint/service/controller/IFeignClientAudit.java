package by.taskManager.reportservice.endpoint.service.controller;


import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.ReportParamAudit;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "audit-service")
public interface IFeignClientAudit {
    @RequestMapping(value = "/service/save",method = RequestMethod.POST)
    ResponseEntity<?> saveItem(AuditDTO audit);

    @RequestMapping(value ="/service/get",method = RequestMethod.GET)
    ResponseEntity<List<AuditDTO>> getList(ReportParamAudit paramAudit);
}
