package by.taskManager.auditservice.service.api;

import by.TaskManeger.utils.dto.UserDTO;

import java.util.Set;
import java.util.UUID;

public interface IUserService {
    UserDTO getInfo(String header);
}
