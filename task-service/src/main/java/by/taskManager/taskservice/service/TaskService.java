package by.taskManager.taskservice.service;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.FilterDTO;
import by.taskManager.taskservice.core.dto.TaskCreateDTO;
import by.taskManager.taskservice.core.dto.TaskDTO;
import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.core.exception.DtUpdateNotCorrectException;
import by.taskManager.taskservice.core.exception.NotCorrectUUIDException;
import by.taskManager.taskservice.dao.entity.TaskEntity;
import by.taskManager.taskservice.dao.api.ITaskData;
import by.taskManager.taskservice.service.api.ITaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static by.taskManager.taskservice.dao.specification.TaskSpecs.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TaskService implements ITaskService {
    private ITaskData taskData;

    public TaskService(ITaskData taskData) {
        this.taskData = taskData;
    }
    @Transactional
    @Override
    public UUID save(TaskCreateDTO dto) {
       TaskEntity entity = new TaskEntity(dto);
        entity.setUuid(UUID.randomUUID());
        taskData.save(entity);
        return entity.getUuid();
    }
    @Transactional(readOnly = true)
    @Override
    public PageDTO getPage(Integer page, Integer size, FilterDTO filter) {
        //todo нужна проверка на доступ пользователя к проектам находящимя фильтре
        Page<TaskEntity> pageResponse = taskData.findAll(
                where(equalsProject(filter.getProjects()))
                .and(equalsImplementer(filter.getImplementers())
                        .and(equalsStatus(filter.getStatus()))),
                PageRequest.of(page, size)
        );
        List<TaskDTO> content = new ArrayList<>();
        for (TaskEntity entity:pageResponse){
            content.add(new TaskDTO(entity));
        }
        return new PageDTO<>(pageResponse,content);
    }
    @Transactional(readOnly = true)
    @Override
    public TaskEntity get(UUID uuid) {
        return taskData.findById(uuid).orElseThrow(() ->
                new NotCorrectUUIDException("Задача не найдена"));
    }
    @Transactional
    @Override
    public UUID upadte(TaskCreateDTO dto, UUID uuid, LocalDateTime dt_update) {
        TaskEntity entity = taskData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        if (!entity.getDtUpdate().equals(dt_update)){
            throw new DtUpdateNotCorrectException("Этот обьект уже кто-то обновил , обновите страницу и повторите попытку!");
        }
        entity.setProject(dto.getProject());
        entity.setTitle(dto.getTitle());
        entity.setDiscription(dto.getDiscription());
        entity.setStatus(TaskStatus.valueOf(dto.getStatus()));
        taskData.save(entity);
        return entity.getUuid();
    }
    @Transactional
    @Override
    public UUID updateСondition(TaskStatus status, UUID uuid, LocalDateTime dt_update) {
        TaskEntity entity = taskData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        entity.setStatus(status);
        taskData.save(entity);
        return entity.getUuid();
    }
}
