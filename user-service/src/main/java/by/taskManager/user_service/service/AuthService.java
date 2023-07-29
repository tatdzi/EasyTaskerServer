package by.taskManager.user_service.service;

import by.taskManager.user_service.core.dto.*;
import by.taskManager.user_service.core.error.StructuredError;
import by.taskManager.user_service.core.exception.StrcturedErrorException;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.IAuthService;
import by.taskManager.user_service.service.api.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.util.UUID;

@Service
public class AuthService implements IAuthService {
    private IUserService userService;
    private MailSenderService mailSender;
    private UserHolder userHolder;


    public AuthService(IUserService userService, MailSenderService mailSender,UserHolder userHolder) {
        this.userService = userService;
        this.mailSender = mailSender;
        this.userHolder = userHolder;
    }

    @Override
    public void save(UserCreateDTO dto) {
        dto.setRole(UserRole.USER.toString());
        dto.setStatus(UserStatus.WAITING_ACTIVATION.toString());
        if (!ObjectUtils.isEmpty(dto.getMail())){
            UserEntity entity = new UserEntity(dto);
            entity.setUuid(UUID.randomUUID());
            String message = String.format(
                    "Welcome to Task Messager. Please, visit next link: " +
                            "http://localhost/users/verification?code=" +
                            entity.getUuid()+"&mail="+entity.getMail()
            );
            mailSender.send(dto.getMail(),"Activation code",message);
            userService.save(entity);
        }
    }
    @Override
    public TokenDTO login(LoginDTO login){
        UserEntity entity = userService.get(login.getMail());
        if (!entity.getPassword().equals(login.getPassword())){
            StrcturedErrorException errorException = new StrcturedErrorException();
            errorException.setError(new StructuredError("password","wrong password"));
        }
        return new TokenDTO(entity);
    }

    @Override
    public UserDTO me() {
        String mail = userHolder.getUser().getMail();
        return new UserDTO(userService.get(mail));
    }

    @Override
    public boolean auth(UUID uuid, String mail) {
        UserEntity entity = userService.get(mail);
        if (entity.getUuid().equals(uuid)){
            entity.setStatus(UserStatus.ACTIVATED);
            userService.save(entity);
            return true;
        }
       return false;
    }
}
