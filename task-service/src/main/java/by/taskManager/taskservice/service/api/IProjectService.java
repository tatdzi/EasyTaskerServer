package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.ProjectDTO;
import by.taskManager.taskservice.dao.Entity.ProjectEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProjectService extends IService<ProjectCreateDTO,ProjectEntity> {
}
