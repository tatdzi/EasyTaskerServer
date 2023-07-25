package by.taskManager.user_service.service.api;

import by.taskManager.user_service.core.dto.PageDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.dao.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    void save(UserCreateDTO dto);
    void save(UserEntity entity);
    UserEntity get(UUID uuid);
    PageDTO getCard(Integer page, Integer size);
    void upadte(UserCreateDTO dto, UUID uuid, LocalDateTime dt_update);
    UserEntity get(UUID uuid, String mail);
}
