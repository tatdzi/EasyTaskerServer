package by.taskManager.user_service.endpoints.web.controller;


import by.TaskManeger.utils.dto.PageDTO;
import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.IUserService;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
private IUserService userService;
private ConversionService conversionService;

    public UserController(IUserService userService,
                          ConversionService conversionService) {
        this.userService = userService;
        this.conversionService = conversionService;
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void doPost(@RequestBody UserCreateDTO dto){
        userService.save(dto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public PageDTO doGet(@RequestParam(name = "page", defaultValue = "0") Integer page,
                         @RequestParam(name = "size", defaultValue = "20") Integer size){
        return userService.getCard(page,size) ;
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO doGet(@PathVariable UUID uuid){
        UserEntity entity = userService.get(uuid);
        return conversionService.convert(entity,UserDTO.class);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PUT,value = "/{uuid}/dt_update/{dt_update}")
    @ResponseStatus(HttpStatus.OK)
    public void doPut(@RequestBody UserCreateDTO dto,
                                   @PathVariable UUID uuid,
                                   @PathVariable LocalDateTime dt_update){
        userService.update(dto,uuid,dt_update);
    }
}
