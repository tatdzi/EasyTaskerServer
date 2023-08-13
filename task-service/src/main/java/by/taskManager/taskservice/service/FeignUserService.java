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
        //if (!response.getStatusCode().equals(HttpStatus.OK)){
        //    throw new RuntimeException("Ошибка авторизации");
        //}
        return userDTO;
    }

    @Override
    public void check(Set<UUID> users) {
        //todo как обработать FiegnEception когда он возвращает статут отличный от 200
        try {
            feignUserController.checkItems(users);
        }catch (FeignException e){
            if (e.status() == 400){
                throw new BadResponsFromService("Имеются несуществующие пользователи");
            }
            if (e.status() == 500){
                throw  new InternalErrorFromService("Сервер не смог обработать запрос");
            }
        }

    }

    @Override
    public void checkManager(UUID manager) {
        try {
            feignUserController.checkItem(manager);
        }catch (FeignException e){
            if (e.status() == 400){
                throw new BadResponsFromService("Такого пользователя не существует или он не имеет прав Менеджера!");
            }
            if (e.status() == 500){
                throw  new InternalErrorFromService("Сервер не смог обработать запрос");
            }
        }

    }
}
