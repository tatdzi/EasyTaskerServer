package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.UserDTO;

public interface IUserService {
    UserDTO getInfo(String header);
}
