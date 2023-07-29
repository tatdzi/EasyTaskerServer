package by.taskManager.user_service.endpoints.web.controllers;

import by.taskManager.user_service.component.JwtTokenUtil;
import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.TokenDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import by.taskManager.user_service.service.api.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registration(@RequestBody UserCreateDTO dto){
        try {
            authService.save(dto);
        }catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @RequestMapping(value = "/verification",method = RequestMethod.GET)
    public ResponseEntity<?> verification(@RequestParam UUID uuid,
                                   @RequestParam String mail){
        authService.auth(uuid,mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginDTO login){
       TokenDTO token = authService.login(login);
        return new ResponseEntity<>(jwtTokenUtil.generateAccessToken(token),HttpStatus.OK);
    }

    @RequestMapping(value = "/me",method = RequestMethod.GET)
    public ResponseEntity<UserDTO> me(){
        UserDTO userDTO = authService.me();
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }
}
