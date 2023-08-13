package by.taskManager.user_service.service.api;

import by.TaskManeger.utils.dto.TokenDTO;
import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.UserRegistrationDTO;
import by.TaskManeger.utils.dto.UserDTO;

import java.util.UUID;

public interface IAuthService {
    UUID save(UserRegistrationDTO dto);
    UUID verify(UUID uuid, String mail);
    TokenDTO login(LoginDTO login);
    UserDTO me();
}
