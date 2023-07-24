package by.taskManager.user_service.service.api;

import by.taskManager.user_service.core.dto.PageOfUserDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.dao.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    void save(UserCreateDTO dto);
    void save(UserEntity entity);
    UserEntity get(UUID uuid);
    PageOfUserDTO getCard(Integer page, Integer size);
    void upadte(UserCreateDTO dto, UUID uuid, LocalDateTime dt_update);
    UserEntity get(String mail, String code);
}
