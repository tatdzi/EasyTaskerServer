package by.taskManager.taskservice.endpoint.web.controller;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.TaskCreateDTO;
import by.taskManager.taskservice.core.dto.TaskDTO;
import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.service.api.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/task")
public class TaskController {
    private ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void doPost(@RequestBody TaskCreateDTO dto){
        taskService.save(dto);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageDTO doGet(@RequestParam(name = "page", defaultValue = "0") Integer page,
                         @RequestParam(name = "size", defaultValue = "20") Integer size){
        return taskService.getPage(page,size) ;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public TaskDTO doGet(@PathVariable UUID uuid){
        return new TaskDTO(taskService.get(uuid));
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{uuid}/dt_update/{dt_update}")
    @ResponseStatus(HttpStatus.OK)
    public void doPut(@RequestBody TaskCreateDTO dto,
                      @PathVariable UUID uuid,
                      @PathVariable LocalDateTime dt_update){
        taskService.upadte(dto,uuid,dt_update);
    }

    @RequestMapping(method = RequestMethod.PATCH,value = "/{uuid}/dt_update/{dt_update}/status/{status}")
    @ResponseStatus(HttpStatus.OK)
    public void doPatch(@PathVariable TaskStatus status,
                        @PathVariable UUID uuid,
                        @PathVariable LocalDateTime dt_update){
        taskService.updateСondition(status,uuid,dt_update);
    }

}
