package by.taskManager.user_service.service.api;

import by.TaskManeger.utils.dto.TokenDTO;
import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import by.taskManager.user_service.core.dto.UserRegistrationDTO;

import java.util.UUID;

public interface IAuthService {
    UUID save(UserRegistrationDTO dto);
    UUID auth(UUID uuid, String mail);
    TokenDTO login(LoginDTO login);
    UserDTO me();
}
