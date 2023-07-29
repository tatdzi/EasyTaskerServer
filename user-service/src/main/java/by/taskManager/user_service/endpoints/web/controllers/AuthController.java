package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.TokenDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import by.taskManager.user_service.endpoints.web.utils.JwtTokenUtil;
import by.taskManager.user_service.service.api.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AuthController {
    private IAuthService authService;
    private JwtTokenUtil jwtTokenUtil;

    public AuthController(IAuthService authService,JwtTokenUtil jwtTokenUtil) {
        this.authService = authService;
        this.jwtTokenUtil = jwtTokenUtil;
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
        return jwtTokenUtil.generateAccessToken(token);
    }

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO me(){
        return authService.me();
    }
}
