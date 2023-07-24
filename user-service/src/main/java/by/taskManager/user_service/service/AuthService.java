package by.taskManager.user_service.service;

import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.enums.UserRole;
import by.taskManager.user_service.core.enums.UserStatus;
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


    public AuthService(IUserService userService, MailSenderService mailSender) {
        this.userService = userService;
        this.mailSender = mailSender;
    }

    @Override
    public void save(UserCreateDTO dto) {
        dto.setRole(UserRole.USER.toString());
        dto.setStatus(UserStatus.WAITING_ACTIVATION.toString());
        if (!ObjectUtils.isEmpty(dto.getMail())){
            String code = UUID.randomUUID().toString();
            dto.setCode(code);
            userService.save(dto);
            String message = String.format(
                    "Welcome to Task Messager. Please, visit next link: " +
                            "http://localhost/users/verification?code=" +
                            code+"&mail="+dto.getMail()
            );
            mailSender.send(dto.getMail(),"Activation code",message);
        }
    }

    @Override
    public boolean auth(String mail, String code) {
        UserEntity entity = userService.get(mail,code);
        if (entity != null){
            entity.setStatus(UserStatus.ACTIVATED);
            userService.save(entity);
            return true;
        }
       return false;
    }
}
