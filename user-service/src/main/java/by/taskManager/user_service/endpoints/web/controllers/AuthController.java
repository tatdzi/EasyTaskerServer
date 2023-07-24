package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import by.taskManager.user_service.service.api.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class AuthController {
    private IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> registration(@RequestBody UserCreateDTO dto){
        try {
            authService.save(dto);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    public ResponseEntity<?> verification(@RequestParam String code,
                                   @RequestParam String mail){
        authService.auth(mail,code);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserCreateDTO dto){

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public UserDTO me(){
        return null;
    }
}
