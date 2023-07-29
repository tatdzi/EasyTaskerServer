package by.taskManager.user_service.service.api;

import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.TokenDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;

import java.util.UUID;

public interface IAuthService {
    UUID save(UserCreateDTO dto);
    UUID auth(UUID uuid, String mail);
    TokenDTO login(LoginDTO login);
    UserDTO me();
}
