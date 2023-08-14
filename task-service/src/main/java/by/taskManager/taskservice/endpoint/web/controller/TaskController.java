package by.taskManager.taskservice.endpoint.web.controller;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.*;
import by.taskManager.taskservice.service.api.ITaskService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void doPost(@RequestBody TaskCreateDTO dto){
        taskService.save(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageDTO doGet(@RequestParam(name = "page", defaultValue = "0") Integer page,
                         @RequestParam(name = "size", defaultValue = "20") Integer size,
                         @RequestParam(name = "project",required = false) List<ProjectRef> projects,
                         @RequestParam(name = "implementer",required = false)List<UserRef> implemeters,
                         @RequestParam(name = "status",required = false)List<TaskStatus> statuses){
        FilterDTO filter = new FilterDTO(projects,implemeters,statuses);
        return taskService.getPage(page,size,filter) ;
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO doGet(@PathVariable UUID uuid){
        return new TaskDTO(taskService.get(uuid));
    }

    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @RequestMapping(method = RequestMethod.PUT,value = "/{uuid}/dt_update/{dt_update}")
    @ResponseStatus(HttpStatus.OK)
    public void doPut(@RequestBody TaskCreateDTO dto,
                      @PathVariable UUID uuid,
                      @PathVariable LocalDateTime dt_update){
        taskService.update(dto,uuid,dt_update);
    }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    @RequestMapping(method = RequestMethod.PATCH,value = "/{uuid}/dt_update/{dt_update}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void doPatch(@PathVariable TaskStatus status,
                        @PathVariable UUID uuid,
                        @PathVariable LocalDateTime dt_update){
        taskService.updateСondition(status,uuid,dt_update);
    }

}
