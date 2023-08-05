package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.FilterDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.TaskCreateDTO;
import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.dao.Entity.ProjectEntity;
import by.taskManager.taskservice.dao.Entity.TaskEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface ITaskService {
    UUID save(TaskCreateDTO dto);
    PageDTO getPage(Integer page, Integer size, FilterDTO filter);
    TaskEntity get(UUID uuid);
    UUID upadte(TaskCreateDTO dto, UUID uuid, LocalDateTime dt_update);
    UUID updateСondition(TaskStatus status, UUID uuid, LocalDateTime dt_update);
}
