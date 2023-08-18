package by.taskManager.taskservice.service;

import by.TaskManeger.utils.dto.EssenceType;
import by.TaskManeger.utils.dto.PageDTO;
import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.TaskManeger.utils.error.StructuredError;
import by.taskManager.taskservice.aop.aspect.Audit;
import by.taskManager.taskservice.aop.aspect.AuditType;
import by.taskManager.taskservice.core.dto.*;
import by.taskManager.taskservice.core.exception.DtUpdateNotCorrectException;
import by.taskManager.taskservice.core.exception.NotCorrectUUIDException;
import by.taskManager.taskservice.core.exception.StrcturedErrorException;
import by.taskManager.taskservice.dao.entity.ProjectEntity;
import by.taskManager.taskservice.dao.entity.TaskEntity;
import by.taskManager.taskservice.dao.api.ITaskData;
import by.taskManager.taskservice.service.api.IProjectService;
import by.taskManager.taskservice.service.api.ITaskService;
import by.taskManager.taskservice.service.component.UserHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.MidiFileFormat;
import java.time.LocalDateTime;
import java.util.*;

import static by.taskManager.taskservice.dao.specification.TaskSpecs.*;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class TaskService implements ITaskService {
    private ITaskData taskData;
    private IProjectService projectService;
    private UserHolder userHolder;

    public TaskService(ITaskData taskData,IProjectService projectService,UserHolder userHolder) {
        this.taskData = taskData;
        this.projectService = projectService;
        this.userHolder = userHolder;
    }
    @Transactional
    @Override
    @Audit(action = AuditType.CREATE_TASK, type = EssenceType.TASK)
    public UUID save(TaskCreateDTO dto) {
       TaskEntity entity = new TaskEntity();
       entity.setDiscription(dto.getDiscription());
       entity.setStatus(TaskStatus.valueOf(dto.getStatus()));
       entity.setProject(projectService.get(dto.getProject().getUuid()));
       entity.setTitle(dto.getTitle());
       entity.setUuid(UUID.randomUUID());

       if (dto.getImplementer() != null){
           ProjectEntity project = projectService.get(dto.getProject().getUuid());
           Set<UUID> users = project.getStaff();
           users.add(project.getManager());
           UUID implementer = dto.getImplementer().getUuid();
           for (UUID user:users){
               if (implementer.equals(user)){
                   entity.setImplementer(implementer);
               }
           }
           if (entity.getImplementer() == null){
               StrcturedErrorException exception = new StrcturedErrorException();
               exception.setError(new StructuredError("implementer",
                       "Данный исполнитель не является учасником проекта"));
           }
       }

        taskData.saveAndFlush(entity);
        return entity.getUuid();
    }
    @Transactional(readOnly = true)
    @Override
    public PageDTO getPage(Integer page, Integer size, FilterDTO filter) {

        filter.setProjects(checkAccsesProject(filter.getProjects()));

        if (filter.getStatus() == null) {
            filter.setStatus(List.of(TaskStatus.CLOSE,
                    TaskStatus.BLOCK,
                    TaskStatus.WAIT,
                    TaskStatus.IN_WORK,
                    TaskStatus.DONE));
        }
        if (filter.getImplementers()==null){
            Page<TaskEntity> pageResponse = taskData.findAll(
                    where(equalsProject(filter.getProjects()))
                            .and(equalsStatus((filter.getStatus()))),
                    PageRequest.of(page, size));
            List<TaskDTO> content = new ArrayList<>();
            for (TaskEntity entity : pageResponse) {
                content.add(new TaskDTO(entity));
            }
            return new PageDTO<>(pageResponse, content);
        }
        Page<TaskEntity> pageResponse = taskData.findAll(
                where(equalsProject(filter.getProjects()))
                        .and(equalsImplementer(filter.getImplementers()))
                        .and(equalsStatus((filter.getStatus()))),
                PageRequest.of(page, size));
        List<TaskDTO> content = new ArrayList<>();
        for (TaskEntity entity : pageResponse) {
            content.add(new TaskDTO(entity));
        }
        return new PageDTO<>(pageResponse, content);
    }




    @Transactional(readOnly = true)
    @Override
    public TaskEntity get(UUID uuid) {
        TaskEntity task = taskData.findById(uuid).orElseThrow(() ->
                new NotCorrectUUIDException("Задача не найдена"));
        ProjectEntity entity = projectService.get(task.getProject().getUuid());
        Set<UUID> users = entity.getStaff();
        users.add(entity.getManager());
        TokenDTO user = userHolder.getUser();
        if (!users.contains(user.getUuid()) && !userHolder.getUser().getRole().equals(UserRole.ADMIN)) {
            throw new NotCorrectUUIDException("ошибка , данный пользователь не имеет прав на получение данной задачи");
        }
        return task;
    }
    @Transactional
    @Override
    @Audit(action = AuditType.UPDATE_TASK, type = EssenceType.TASK)
    public UUID update(TaskCreateDTO dto, UUID uuid, LocalDateTime dt_update) {
        TaskEntity entity = taskData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        if (!entity.getDtUpdate().equals(dt_update)){
            throw new DtUpdateNotCorrectException("Этот обьект уже кто-то обновил , обновите страницу и повторите попытку!");
        }
        ProjectEntity projectEntity = projectService.get(dto.getProject().getUuid());
        entity.setProject(projectEntity);
        entity.setTitle(dto.getTitle());
        entity.setDiscription(dto.getDiscription());
        entity.setStatus(TaskStatus.valueOf(dto.getStatus()));
        taskData.saveAndFlush(entity);
        return entity.getUuid();
    }
    @Transactional
    @Override
    @Audit(action = AuditType.UPDATE_STATUS_OF_TASK, type = EssenceType.TASK)
    public UUID updateСondition(TaskStatus status, UUID uuid, LocalDateTime dt_update) {
        TaskEntity entity = taskData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        entity.setStatus(status);
        taskData.saveAndFlush(entity);
        return entity.getUuid();
    }

    public List<ProjectRef> checkAccsesProject(List<ProjectRef> filter){
        TokenDTO user = userHolder.getUser();
        if (filter == null) {
            if (user.getRole().equals(UserRole.ADMIN)) {
                List<ProjectEntity> projectEntities = projectService.getAll();
                List<ProjectRef> refs = new ArrayList<>();
                for (ProjectEntity entity:projectEntities){
                    refs.add(new ProjectRef(entity.getUuid()));
                }
                filter = refs;
            }
            if (!user.getRole().equals(UserRole.ADMIN)) {
                List<ProjectEntity> projectEntities = projectService.getByUser(user.getUuid());
                List<ProjectRef> projectRefs1 = new ArrayList<>();
                for (ProjectEntity entity : projectEntities) {
                    projectRefs1.add(new ProjectRef(entity.getUuid()));
                }
                filter = projectRefs1 ;
            }
        }else if (!user.getRole().equals(UserRole.ADMIN)){
            List<ProjectRef> projectRefs = new ArrayList<>();
            for (ProjectRef entity : filter) {
                ProjectEntity project = projectService.get(entity.getUuid());
                if (project.getManager().equals(user.getUuid()) || project.getStaff().contains(user.getUuid())){
                    projectRefs.add(new ProjectRef(entity.getUuid()));
                }
            }
            filter = projectRefs;
        }
        return filter;
    }
}
