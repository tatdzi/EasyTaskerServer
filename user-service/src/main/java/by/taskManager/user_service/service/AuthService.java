package by.taskManager.user_service.service;

import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.TaskManeger.utils.error.StructuredError;
import by.taskManager.user_service.core.dto.LoginDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserRegistrationDTO;
import by.taskManager.user_service.core.dto.UserStatus;
import by.taskManager.user_service.core.exception.StrcturedErrorException;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.IAuthService;
import by.taskManager.user_service.service.api.IUserService;
import by.taskManager.user_service.service.component.UserHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService implements IAuthService {
    private IUserService userService;
    private UserHolder userHolder;
    private PasswordEncoder passwordEncoder;


    public AuthService(IUserService userService,UserHolder userHolder,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userHolder = userHolder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UUID save(UserRegistrationDTO dto) {
        UserCreateDTO user = new UserCreateDTO(
                dto.getMail(),
                dto.getFio(),
                UserRole.USER.toString(),
                UserStatus.WAITING_ACTIVATION.toString(),
                dto.getPassword()
        );
        return userService.save(user);
    }
    @Override
    public TokenDTO login(LoginDTO login){
        UserEntity entity = userService.get(login.getMail());
        if (!this.passwordEncoder.matches(login.getPassword(),entity.getPassword())){
            StrcturedErrorException errorException = new StrcturedErrorException();
            errorException.setError(new StructuredError("password","wrong password"));
            throw errorException;
        }
        return new TokenDTO(entity.getUuid(),entity.getMail(),entity.getFio(),entity.getRole());
    }

    @Override
    public UserDTO me() {
        String mail = userHolder.getUser().getMail();
        UserEntity entity = userService.get(mail);
        UserDTO user = new UserDTO(
                entity.getUuid().toString(),
                entity.getDtCreate(),
                entity.getDtUpdate(),
                entity.getMail(),
                entity.getFio(),
                entity.getRole().toString(),
                entity.getStatus().toString()
        );
        return user;
    }

    @Override
    public UUID verify(UUID uuid, String mail) {
        UserEntity entity = userService.get(mail);
        if (!entity.getUuid().equals(uuid)){
          throw new RuntimeException("неверный код авторизции!");
        }
        entity.setStatus(UserStatus.ACTIVATED);
        userService.update(new UserCreateDTO(entity),uuid,entity.getDtUpdate());
        return entity.getUuid();
    }
}
