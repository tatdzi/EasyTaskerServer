package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.TaskCreateDTO;
import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.dao.Entity.ProjectEntity;
import by.taskManager.taskservice.dao.Entity.TaskEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ITaskService extends IService<TaskCreateDTO, TaskEntity> {
    UUID updateСondition(TaskStatus status, UUID uuid, LocalDateTime dt_update);
}
