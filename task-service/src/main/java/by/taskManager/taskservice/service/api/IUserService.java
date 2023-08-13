package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.UserDTO;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IUserService {
    UserDTO getInfo(String header);
    void check(Set<UUID> users);
    void checkManager(UUID manager);
}
