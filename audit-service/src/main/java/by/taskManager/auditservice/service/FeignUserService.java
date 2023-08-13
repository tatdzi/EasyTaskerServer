package by.taskManager.auditservice.service;

import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.auditservice.endpoint.service.controller.IFeignClienUser;
import by.taskManager.auditservice.service.api.IUserService;
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



}
