package by.taskManager.reportservice.endpoint.web.controller;


import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.reportservice.core.dto.FileDTO;
import by.taskManager.reportservice.core.dto.ReportType;
import by.taskManager.reportservice.service.api.IReportService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(method = RequestMethod.POST,value = "/{type}")
    @ResponseStatus(HttpStatus.CREATED)
    public void doPost(@RequestBody Map<String,Object> param, @PathVariable ReportType type){
        reportService.create(param,type);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageDTO getPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                      @RequestParam(name = "size", defaultValue = "20") Integer size){
        return reportService.getPage(page,size);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{uuid}/export")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ByteArrayResource> export(@PathVariable UUID uuid){
        FileDTO export = this.reportService.download(uuid);
        ByteArrayResource resource = new ByteArrayResource(export.getContent());
        return ResponseEntity
                .ok()
                .contentLength(export.getContent().length)
                .header("Content-type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
                .header("Content-disposition", "attachment; filename=\"" + export.getFileName() + "\"")
                .body(resource);
    }


    @RequestMapping(method = RequestMethod.HEAD, value = "/{uuid}/export")
    public ResponseEntity<?> checkIfReady(@PathVariable UUID uuid) {

        boolean reportAvailable = reportService.isReady(uuid);

        if (reportAvailable) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
