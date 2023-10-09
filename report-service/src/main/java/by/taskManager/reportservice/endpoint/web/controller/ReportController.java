package by.taskManager.reportservice.endpoint.web.controller;


import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.reportservice.core.dto.FileDTO;
import by.taskManager.reportservice.core.dto.ReportType;
import by.taskManager.reportservice.service.api.IReportService;
import jakarta.annotation.security.PermitAll;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/report")
public class ReportController {
    private IReportService reportService;

    public ReportController(IReportService reportService) {
        this.reportService = reportService;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST,value = "/{type}")
    @ResponseStatus(HttpStatus.CREATED)
    public void doPost(@RequestBody Map<String,String> param, @PathVariable ReportType type){
        reportService.create(param,type);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageDTO getPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                      @RequestParam(name = "size", defaultValue = "20") Integer size){
        return reportService.getPage(page,size);
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET,value = "/{uuid}/export")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> export(@PathVariable UUID uuid){
        String export = this.reportService.download(uuid);
        return ResponseEntity
                .ok()
                .header("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .header("Content-disposition", "attachment; filename=\"" + export + "\"")
                .body(export);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.HEAD, value = "/{uuid}/export")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> checkIfReady(@PathVariable UUID uuid) {
        boolean is = this.reportService.isReady(uuid);
        if (is){
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
