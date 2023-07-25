package by.taskManager.user_service.service.api;

import by.taskManager.user_service.core.dto.UserCreateDTO;

import java.util.UUID;

public interface IAuthService {
    void save(UserCreateDTO dto);
    boolean auth(UUID uuid, String mail);
}
