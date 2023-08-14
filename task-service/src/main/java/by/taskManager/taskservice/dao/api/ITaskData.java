package by.taskManager.taskservice.dao.api;

import by.taskManager.taskservice.dao.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ITaskData extends JpaRepository<TaskEntity, UUID>,
        PagingAndSortingRepository<TaskEntity, UUID>,
        JpaSpecificationExecutor<TaskEntity> {

}
