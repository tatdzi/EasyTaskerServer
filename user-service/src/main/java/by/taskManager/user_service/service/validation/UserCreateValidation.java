package by.taskManager.user_service.service.validation;

import by.TaskManeger.utils.error.StructuredError;
import by.taskManager.user_service.core.dto.UserCreateDTO;
import by.taskManager.user_service.core.exception.StrcturedErrorException;
import by.taskManager.user_service.service.validation.api.Validation;
import org.springframework.stereotype.Component;

@Component
public class UserCreateValidation implements Validation<UserCreateDTO> {
    @Override
    public void validation(UserCreateDTO item) {
        StrcturedErrorException errorResponse = new StrcturedErrorException();
        if (item.getMail()==null){
            errorResponse.setError(new StructuredError("mail","Обязательное для заплнения поле!"));
        }
        if (item.getFio()==null){
            errorResponse.setError(new StructuredError("fio","Обязательное для заплнения поле!"));
        }
        if (item.getPassword()==null){
            errorResponse.setError(new StructuredError("password","Обязательное для заплнения поле!"));
        }
        if (item.getRole()==null){
            errorResponse.setError(new StructuredError("role","Обязательное для заплнения поле!"));
        }
        if (item.getStatus()==null){
            errorResponse.setError(new StructuredError("status","Обязательное для заплнения поле!"));
        }
        if (errorResponse.getErrors().size() > 0){
            throw errorResponse;
        }
    }
}
