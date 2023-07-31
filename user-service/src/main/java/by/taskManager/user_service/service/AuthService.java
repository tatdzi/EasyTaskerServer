package by.taskManager.user_service.service;

import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.TaskManeger.utils.error.StructuredError;
import by.taskManager.user_service.core.dto.*;
import by.taskManager.user_service.core.exception.StrcturedErrorException;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.IAuthService;
import by.taskManager.user_service.service.api.IUserService;
import by.taskManager.user_service.service.component.UserHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements IAuthService {
    private IUserService userService;
    private UserHolder userHolder;


    public AuthService(IUserService userService,UserHolder userHolder) {
        this.userService = userService;
        this.userHolder = userHolder;
    }

    @Override
    public UUID save(UserCreateDTO dto) {
        dto.setRole(UserRole.USER.toString());
        dto.setStatus(UserStatus.WAITING_ACTIVATION.toString());
        return userService.save(dto);
    }
    @Override
    public TokenDTO login(LoginDTO login){
        UserEntity entity = userService.get(login.getMail());
        if (!entity.getPassword().equals(login.getPassword())){
            StrcturedErrorException errorException = new StrcturedErrorException();
            errorException.setError(new StructuredError("password","wrong password"));
        }
        return new TokenDTO(entity.getUuid(),entity.getMail(),entity.getFio(),entity.getRole());
    }

    @Override
    public UserDTO me() {
        String mail = userHolder.getUser().getMail();
        return new UserDTO(userService.get(mail));
    }

    @Override
    public UUID auth(UUID uuid, String mail) {
        UserEntity entity = userService.get(mail);
        if (!entity.getUuid().equals(uuid)){
          throw new RuntimeException("неверный код авторизции!");
        }
        entity.setStatus(UserStatus.ACTIVATED);
        userService.upadte(new UserCreateDTO(entity),uuid,entity.getDtUpdate());
        return entity.getUuid();
    }
}
