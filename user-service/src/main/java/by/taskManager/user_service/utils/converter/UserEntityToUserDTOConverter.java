package by.taskManager.user_service.utils.converter;

import by.TaskManeger.utils.dto.UserDTO;
import by.taskManager.user_service.dao.entity.UserEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntityToUserDTOConverter implements Converter<UserEntity,UserDTO> {

    @Override
    public UserDTO convert(UserEntity source) {
        UserDTO user = new UserDTO();
        user.setFio(source.getFio());
        user.setMail(source.getMail());
        user.setRole(source.getRole());
        user.setStatus(source.getStatus());
        user.setUuid(source.getUuid());
        user.setDtCreate(source.getDtCreate());
        user.setDtUpdate(source.getDtUpdate());
        return user;
    }
}
