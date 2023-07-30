package by.taskManager.user_service.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.dao.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {
    UUID save(UserCreateDTO dto);
    UserEntity get(UUID uuid);
    PageDTO getCard(Integer page, Integer size);
    UUID upadte(UserCreateDTO dto, UUID uuid, LocalDateTime dt_update);
    UserEntity get(String mail);
}
