package by.taskManager.user_service.endpoints.web.controller;

import by.TaskManeger.utils.dto.TokenDTO;
import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import by.taskManager.user_service.endpoints.handler.JwtTokenHandler;
import by.taskManager.user_service.service.api.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AuthController {
    private IAuthService authService;
    private JwtTokenHandler jwtTokenHandler;

    public AuthController(IAuthService authService, JwtTokenHandler jwtTokenHandler) {
        this.authService = authService;
        this.jwtTokenHandler = jwtTokenHandler;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody UserCreateDTO dto){
            authService.save(dto);
    }


    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void verification(@RequestParam UUID code,
                         @RequestParam String mail){
        authService.auth(code,mail);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDTO login){
       TokenDTO token = authService.login(login);
        return jwtTokenHandler.generateAccessToken(token);
    }

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO me(){
        return authService.me();
    }
}
