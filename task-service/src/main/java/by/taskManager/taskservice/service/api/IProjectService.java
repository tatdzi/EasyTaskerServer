package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.ProjectDTO;
import by.taskManager.taskservice.dao.Entity.ProjectEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IProjectService {
    UUID save(ProjectCreateDTO dto);
    PageDTO getPage(Integer page, Integer size,Boolean archived);
    ProjectEntity get(UUID uuid);
    UUID upadte(ProjectCreateDTO dto, UUID uuid, LocalDateTime dt_update);
}
