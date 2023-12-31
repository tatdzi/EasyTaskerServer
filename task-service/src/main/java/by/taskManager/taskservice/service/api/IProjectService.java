package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.dao.entity.ProjectEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IProjectService {
    UUID save(ProjectCreateDTO dto);
    PageDTO getPage(Integer page, Integer size,Boolean archived);
    ProjectEntity get(UUID uuid);
    UUID update(ProjectCreateDTO dto, UUID uuid, LocalDateTime dt_update);
    List<ProjectEntity> getByUser(UUID uuid);
    List<ProjectEntity> getAll();
}
