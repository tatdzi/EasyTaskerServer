package by.taskManager.taskservice.service.component;

import by.TaskManeger.utils.dto.TokenDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserHolder {

    public TokenDTO getUser(){
        return (TokenDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
