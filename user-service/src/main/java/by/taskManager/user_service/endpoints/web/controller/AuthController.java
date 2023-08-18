package by.taskManager.user_service.endpoints.web.controller;

import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.UserRegistrationDTO;
import by.taskManager.user_service.endpoints.handler.JwtTokenHandler;
import by.taskManager.user_service.service.api.IAuthService;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PermitAll
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void registration(@RequestBody UserRegistrationDTO dto){
            authService.save(dto);
    }

    @PermitAll
    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void verification(@RequestParam UUID code,
                         @RequestParam String mail){
        authService.verify(code,mail);
    }

    @PermitAll
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody LoginDTO login){
       TokenDTO token = authService.login(login);
        return jwtTokenHandler.generateAccessToken(token);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/me",method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO me(){
        return authService.me();
    }
}
