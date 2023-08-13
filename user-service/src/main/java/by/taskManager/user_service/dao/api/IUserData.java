package by.taskManager.user_service.dao.api;

import by.taskManager.user_service.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IUserData extends JpaRepository<UserEntity,UUID>, PagingAndSortingRepository<UserEntity, UUID> {
    UserEntity findByMail(String mail);
    boolean existsByMail(String mail);
}
