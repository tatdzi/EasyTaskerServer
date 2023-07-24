package by.taskManager.user_service.dao.api;

import by.taskManager.user_service.dao.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IUserData extends CrudRepository<UserEntity,UUID> , PagingAndSortingRepository<UserEntity, UUID> {
    UserEntity findByMailAndCode(String mail,String code);
}
