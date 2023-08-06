package by.taskManager.taskservice.service;

import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.taskservice.endpoint.service.controller.IFeignUserController;
import by.taskManager.taskservice.service.api.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeignUserService implements IUserService {
    private IFeignUserController feignUserController;

    public FeignUserService(IFeignUserController feignUserController) {
        this.feignUserController = feignUserController;
    }

    @Override
    public UserDTO getInfo(String header) {
        ResponseEntity response = feignUserController.saveItem(header);
        if (!response.getStatusCode().equals(HttpStatus.OK)){
            throw new RuntimeException("Ошибка авторизации");
        }
        //todo приведение типов
        UserDTO dto = (UserDTO) response.getBody();
        return dto;
    }
}
