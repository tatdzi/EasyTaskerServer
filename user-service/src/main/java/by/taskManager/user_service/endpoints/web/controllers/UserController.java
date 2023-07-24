package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.core.dto.PageOfUserDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import by.taskManager.user_service.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
private IUserService userService;
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> doPost(@RequestBody UserCreateDTO dto){
        userService.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageOfUserDTO> doGet(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                @RequestParam(name = "size", defaultValue = "20") Integer size){
        return new ResponseEntity<>(userService.getCard(page,size),HttpStatus.OK) ;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/{uuid}")
    public ResponseEntity<UserDTO> doGet(@PathVariable UUID uuid){
        return new ResponseEntity<>(new UserDTO(userService.get(uuid)),HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.PUT,value = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> doPut(@RequestBody UserCreateDTO dto,
                                   @PathVariable UUID uuid,
                                   @PathVariable LocalDateTime dt_update){
        userService.upadte(dto,uuid,dt_update);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
