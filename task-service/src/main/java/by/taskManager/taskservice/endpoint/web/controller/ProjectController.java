package by.taskManager.taskservice.endpoint.web.controller;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.ProjectDTO;
import by.taskManager.taskservice.service.api.IProjectService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private IProjectService projectService;

    public ProjectController(IProjectService projectService) {
        this.projectService = projectService;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void doPost(@RequestBody ProjectCreateDTO dto){
        projectService.save(dto);
    }
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageDTO doGet(@RequestParam(name = "page", defaultValue = "0") Integer page,
                         @RequestParam(name = "size", defaultValue = "20") Integer size,
                         @RequestParam(name = "archived", defaultValue = "false") Boolean archived){
        return projectService.getPage(page,size,archived) ;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ProjectDTO doGet(@PathVariable UUID uuid){
        return new ProjectDTO(projectService.get(uuid));
    }


    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @RequestMapping(method = RequestMethod.PUT,value = "/{uuid}/dt_update/{dt_update}")
    @ResponseStatus(HttpStatus.OK)
    public void doPut(@RequestBody ProjectCreateDTO dto,
                      @PathVariable UUID uuid,
                      @PathVariable LocalDateTime dt_update){
        projectService.update(dto,uuid,dt_update);
    }
}
