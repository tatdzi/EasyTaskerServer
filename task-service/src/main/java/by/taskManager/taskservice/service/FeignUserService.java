package by.taskManager.taskservice.service;

import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.taskservice.core.exception.BadResponsFromService;
import by.taskManager.taskservice.core.exception.InternalErrorFromService;
import by.taskManager.taskservice.endpoint.service.controller.IFeignClienUser;
import by.taskManager.taskservice.service.api.IUserService;
import feign.FeignException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.Set;
import java.util.UUID;

@Service
public class FeignUserService implements IUserService {
    private IFeignClienUser feignUserController;

    public FeignUserService(IFeignClienUser feignUserController) {
        this.feignUserController = feignUserController;
    }

    @Override
    public UserDTO getInfo(String header) {
        ResponseEntity<UserDTO> response = feignUserController.saveItem(header);
        UserDTO userDTO = response.getBody();
        return userDTO;
    }

    @Override
    public void check(Set<UUID> users) {
        //todo как обработать FiegnEception когда он возвращает статут отличный от 200
            feignUserController.checkItems(users);
    }

    @Override
    public void checkManager(UUID manager) {
        feignUserController.checkItem(manager);
    }
}
