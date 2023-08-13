package by.taskManager.user_service.endpoints.web.controller;

import by.taskManager.user_service.service.api.IUserServiceIn;
import jakarta.annotation.security.PermitAll;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user-service")
public class ServiceController {
    private IUserServiceIn userServiceIn;

    public ServiceController(IUserServiceIn userServiceIn) {
        this.userServiceIn = userServiceIn;
    }
    @PermitAll
    @RequestMapping(value = "/check",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    void check(@RequestBody List<UUID> items){
        userServiceIn.check(items);
    }
    @PermitAll
    @RequestMapping(value = "/check/{uuid}",method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    void check(@PathVariable UUID uuid){
        userServiceIn.checkManager(uuid);
    }
}
