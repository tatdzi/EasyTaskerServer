package by.taskManager.reportservice.endpoint.web.controller;


import by.taskManager.reportservice.core.dto.ReportParamAudit;
import by.taskManager.reportservice.core.dto.ReportType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/report")
public class ReportController {

    @RequestMapping(method = RequestMethod.POST,value = "/{type}")
    @ResponseStatus(HttpStatus.CREATED)
    public void doPost(@RequestBody ReportParamAudit param,@PathVariable ReportType type){
        projectService.save(dto);
    }



}
