package by.taskManager.user_service.service.api;

import by.taskManager.user_service.core.dto.UserCreateDTO;

public interface IAuthService {
    void save(UserCreateDTO dto);
    boolean auth(String mail,String code);
}
