package by.taskManager.user_service.service;

import by.taskManager.user_service.core.dto.*;
import by.taskManager.user_service.core.exception.DtUpdateNotCorrectException;
import by.taskManager.user_service.core.exception.NotCorrectUUIDException;
import by.taskManager.user_service.core.exception.StrcturedErrorException;
import by.taskManager.user_service.core.error.StructuredError;
import by.taskManager.user_service.dao.api.IUserData;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.IUserService;
import by.taskManager.user_service.service.validation.api.Validation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private IUserData userData;
    private Validation<UserCreateDTO> validation;

    public UserService(IUserData userData, Validation<UserCreateDTO> validatio) {
        this.userData = userData;
        this.validation = validatio;
    }

    @Override
    public UUID save(UserCreateDTO dto){
        validation.validation(dto);
        UserEntity entity = new UserEntity(dto);
        entity.setUuid(UUID.randomUUID());
        if (userData.existsByMail(entity.getMail())){
            StrcturedErrorException errorException = new StrcturedErrorException();
            errorException.setError(new StructuredError("mail","Такой адрес электронной почты уже существует"));
            throw errorException;
        }
        if (entity.getStatus().equals(UserStatus.WAITING_ACTIVATION)){
            if (!ObjectUtils.isEmpty(dto.getMail())){
                String message = String.format(
                        "Welcome to Task Messager. Please, visit next link: " +
                                "http://localhost/users/verification?code=" +
                                entity.getUuid()+"&mail="+entity.getMail()
                );
                mailSender.send(dto.getMail(),"Activation code",message);
                userService.save(entity);
            }
        }
        userData.save(entity);
        return entity.getUuid();
    }


    @Override
    public UserEntity get(UUID uuid) {
        return userData.findById(uuid).orElseThrow(() ->
                new NotCorrectUUIDException("Пользователь с таким uuid не найден"));
    }

    @Override
    public PageDTO getCard(Integer page, Integer size) {
        Page<UserEntity> pageResponse = userData.findAll(PageRequest.of(page, size));
        List<UserDTO> content = new ArrayList<>();

        for (UserEntity entity:pageResponse){
            content.add(new UserDTO(entity));
        }
        return new PageDTO<>(pageResponse,content);
    }

    @Override
    public void upadte( UserCreateDTO dto, UUID uuid, LocalDateTime dt_update) {
        UserEntity entity = userData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        if (!entity.getDtUpdate().equals(dt_update)){
            throw new DtUpdateNotCorrectException("Этот обьект уже кто-то обновил , обновите страницу и повторите попытку!");
        }
        if (!dto.getMail().equals(entity.getMail())){
            if (userData.existsByMail(entity.getMail())){
                StrcturedErrorException errorException = new StrcturedErrorException();
                errorException.setError(new StructuredError("mail","Такой адрес электронной почты уже существует"));
                throw errorException;
            }
        }
        entity.setStatus(UserStatus.valueOf(dto.getStatus()));
        entity.setRole(UserRole.valueOf(dto.getRole()));
        entity.setFio(dto.getFio());
        entity.setMail(dto.getMail());
        entity.setPassword(dto.getPassword());
        save(entity);
    }

    @Override
    public UserEntity get(String mail) {
        return userData.findByMail(mail);
    }
}
