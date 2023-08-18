package by.taskManager.user_service.service;

import by.TaskManeger.utils.dto.UserRole;
import by.taskManager.user_service.core.exception.NotCorrectUUIDException;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.IUserService;
import by.taskManager.user_service.service.api.IUserServiceIn;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
@Service
public class UserServiceService implements IUserServiceIn {
    private IUserService userService;

    public UserServiceService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void check(List<UUID> items) {
        for (UUID uuid:items){
                userService.get(uuid);
        }
    }

    @Override
    public void checkManager(UUID admin) {
        UserEntity user = userService.get(admin);
        if (!user.getRole().equals(UserRole.MANAGER)){
            throw new NotCorrectUUIDException("Пользователь не являетя Менеджером");
        }
    }
}
