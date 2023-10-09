package by.taskManager.user_service.service;

import by.TaskManeger.utils.dto.MailDetails;
import by.TaskManeger.utils.dto.PageDTO;
import by.TaskManeger.utils.dto.UserDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.TaskManeger.utils.error.StructuredError;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.TaskManeger.utils.dto.UserStatus;
import by.taskManager.user_service.core.exception.DtUpdateNotCorrectException;
import by.taskManager.user_service.core.exception.NotCorrectUUIDException;
import by.taskManager.user_service.core.exception.StrcturedErrorException;
import by.taskManager.user_service.dao.api.IUserData;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.INotificationService;
import by.taskManager.user_service.service.api.IUserService;
import by.taskManager.user_service.service.validation.api.Validation;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private String ERROR_OF_UPDATE = "Этот обьект уже кто-то обновил , обновите страницу и повторите попытку!";
    private String ERROR_OF_MAIL = "Такой адрес электронной почты уже существует";
    private String ERROR_OF_UUID = "Пользователь с таким uuid не найден";
    private IUserData userData;
    private Validation validation;
    private INotificationService mailService;
    private PasswordEncoder passwordEncoder;
    private ConversionService conversionService;

    public UserService(IUserData userData,
                       Validation validatio,
                       INotificationService mailService,
                       PasswordEncoder passwordEncoder,
                       ConversionService conversionService) {
        this.userData = userData;
        this.validation = validatio;
        this.mailService =mailService;
        this.passwordEncoder = passwordEncoder;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public UUID save(UserCreateDTO dto){
        validation.validation(dto);
        UserEntity entity = new UserEntity(dto);
        entity.setUuid(UUID.randomUUID());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (userData.existsByMail(entity.getMail())){
            StrcturedErrorException errorException = new StrcturedErrorException();
            errorException.setError(new StructuredError("mail", ERROR_OF_MAIL));
            throw errorException;
        }
        userData.saveAndFlush(entity);
        if (entity.getStatus().equals(UserStatus.WAITING_ACTIVATION)){
            MailDetails mailDetails =
                    new MailDetails(
                            entity.getMail(),
                            entity.getUuid()
                    );
            mailService.sendLetter(mailDetails);
        }
        return entity.getUuid();
    }



    @Override
    @Transactional(readOnly = true)
    public UserEntity get(UUID uuid) throws NotCorrectUUIDException {
        return userData.findById(uuid).orElseThrow(() ->
                new NotCorrectUUIDException(ERROR_OF_UUID));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTO getCard(Integer page, Integer size) {
        Page<UserEntity> pageResponse = userData.findAll(PageRequest.of(page, size));
        List<UserDTO> content = new ArrayList<>();
        for (UserEntity entity:pageResponse){
            content.add(conversionService.convert(entity,UserDTO.class));
        }
        return new PageDTO<>(pageResponse,content);
    }

    @Override
    @Transactional
    public UUID update(UserCreateDTO dto, UUID uuid, LocalDateTime dt_update) {
        UserEntity entity = userData.findById(uuid).orElseThrow(()->
                new NotCorrectUUIDException(ERROR_OF_UUID));
        if (!entity.getDtUpdate().equals(dt_update)){
            throw new DtUpdateNotCorrectException(ERROR_OF_UPDATE);
        }
        if (!dto.getMail().equals(entity.getMail())){
            if (userData.existsByMail(entity.getMail())){
                StrcturedErrorException errorException = new StrcturedErrorException();
                errorException.setError(new StructuredError("mail", ERROR_OF_MAIL));
                throw errorException;
            }
        }
        if (!entity.getPassword().equals(dto.getPassword())){
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        }else {
            entity.setPassword(dto.getPassword());
        }
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        entity.setFio(dto.getFio());
        entity.setMail(dto.getMail());
        userData.saveAndFlush(entity);
        return entity.getUuid();
    }

    @Override
    @Transactional(readOnly = true)
    public UserEntity get(String mail) {
        return userData.findByMail(mail);
    }
}
