package by.taskManager.taskservice.service.api;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.dao.Entity.ProjectEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IService <T,S>{
    UUID save(T dto);
    PageDTO getPage(Integer page, Integer size);
    S get(UUID uuid);
    UUID upadte(T dto, UUID uuid, LocalDateTime dt_update);
}
