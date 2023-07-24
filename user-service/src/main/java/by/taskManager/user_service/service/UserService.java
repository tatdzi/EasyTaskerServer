package by.taskManager.user_service.service;

import by.taskManager.user_service.core.dto.PageOfUserDTO;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.dto.UserDTO;
import by.taskManager.user_service.core.enums.UserRole;
import by.taskManager.user_service.core.enums.UserStatus;
import by.taskManager.user_service.core.error.StrcturedErrorException;
import by.taskManager.user_service.core.error.StructuredError;
import by.taskManager.user_service.dao.api.IUserData;
import by.taskManager.user_service.dao.entity.UserEntity;
import by.taskManager.user_service.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private IUserData userData;

    public UserService(IUserData userData) {
        this.userData = userData;
    }

    @Override
    public void save(UserCreateDTO dto){
        validation(dto);
        UserEntity entity = new UserEntity(dto);
        entity.setUuid(UUID.randomUUID());
        save(entity);
    }

    @Override
    public void save(UserEntity entity){
            userData.save(entity);
    }


    @Override
    public UserEntity get(UUID uuid) {
        return userData.findById(uuid).orElseThrow(() -> new IllegalArgumentException("Не могу найти тебя"));
    }

    @Override
    public PageOfUserDTO getCard(Integer page, Integer size) {
        Page<UserEntity> pageResponse = userData.findAll(PageRequest.of(page, size));
        List<UserDTO> content = new ArrayList<>();

        for (UserEntity entity:pageResponse){
            content.add(new UserDTO(entity));
        }
        return new PageOfUserDTO<>(pageResponse,content);
    }

    @Override
    public void upadte( UserCreateDTO dto, UUID uuid, LocalDateTime dt_update) {
        UserEntity entity = userData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        if (entity.getDtUpdate().equals(dt_update)){
            entity.setStatus(UserStatus.valueOf(dto.getStatus()));
            entity.setRole(UserRole.valueOf(dto.getRole()));
            entity.setFio(dto.getFio());
            entity.setMail(dto.getMail());
            entity.setPassword(dto.getPassword());
            userData.save(entity);
        }
    }

    @Override
    public UserEntity get(String mail, String code) {
        return userData.findByMailAndCode(mail,code);
    }
    public void validation(UserCreateDTO dto){
        StrcturedErrorException errorResponse = new StrcturedErrorException();
        if (dto.getMail()==null){
            errorResponse.setError(new StructuredError("mail","Обязательное для заплнения поле!"));
        }
        if (dto.getFio()==null){
            errorResponse.setError(new StructuredError("fio","Обязательное для заплнения поле!"));
        }
        if (dto.getPassword()==null){
            errorResponse.setError(new StructuredError("password","Обязательное для заплнения поле!"));
        }
        if (dto.getRole()==null){
            errorResponse.setError(new StructuredError("role","Обязательное для заплнения поле!"));
        }
        if (dto.getStatus()==null){
            errorResponse.setError(new StructuredError("status","Обязательное для заплнения поле!"));
        }
        if (errorResponse.getErrors().size() > 0){
            throw errorResponse;
        }
    }


}
