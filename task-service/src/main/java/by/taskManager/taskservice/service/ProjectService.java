package by.taskManager.taskservice.service;

import by.TaskManeger.utils.dto.EssenceType;
import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.aop.aspect.Audit;
import by.taskManager.taskservice.aop.aspect.AuditType;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.ProjectDTO;
import by.taskManager.taskservice.core.dto.ProjectStatus;
import by.taskManager.taskservice.core.dto.UserRef;
import by.taskManager.taskservice.core.exception.DtUpdateNotCorrectException;
import by.taskManager.taskservice.core.exception.NotCorrectUUIDException;
import by.taskManager.taskservice.dao.entity.ProjectEntity;
import by.taskManager.taskservice.dao.api.IProjectData;
import by.taskManager.taskservice.service.api.IProjectService;
import by.taskManager.taskservice.service.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectService implements IProjectService {
    private IProjectData projectData;
    private IUserService userService;

    public ProjectService(IProjectData projectData,IUserService userService) {
        this.projectData = projectData;
        this.userService = userService;
    }

    @Transactional
    @Override
    @Audit(action = AuditType.CREATE_PROJECT, type = EssenceType.PROJECT)
    public UUID save(ProjectCreateDTO dto) {
        ProjectEntity entity = new ProjectEntity(dto);
        entity.setUuid(UUID.randomUUID());

        if (dto.getManager() !=null){
            userService.checkManager(dto.getManager().getUuid());
            entity.setManager(dto.getManager().getUuid());
        }
        Set<UUID> users = new HashSet<>();
        if (dto.getStaff()!=null){
            for (UserRef user:dto.getStaff()){
                users.add(user.getUuid());
            }
            userService.check(users);
            entity.setStaff(users);
        }
        entity.setDiscription(dto.getDiscription());
        entity.setName(dto.getName());
        entity.setStatus(ProjectStatus.valueOf(dto.getStatus()));
        projectData.save(entity);
        return entity.getUuid();
    }
    @Transactional(readOnly = true)
    @Override
    public PageDTO getPage(Integer page, Integer size,Boolean archived) {
        if (archived){
            Page<ProjectEntity> pageResponse = projectData.findAll(PageRequest.of(page, size));
            List<ProjectDTO> content = new ArrayList<>();
            for (ProjectEntity entity:pageResponse){
                content.add(new ProjectDTO(entity));
            }
            return new PageDTO<>(pageResponse,content);
        }
        Page<ProjectEntity> pageResponse = projectData.findByStatusIs(PageRequest.of(page, size),ProjectStatus.ACTIVE);
        List<ProjectDTO> content = new ArrayList<>();
        for (ProjectEntity entity:pageResponse){
            content.add(new ProjectDTO(entity));
        }
        return new PageDTO<>(pageResponse,content);


    }
    @Transactional(readOnly = true)
    @Override
    public ProjectEntity get(UUID uuid) {
        return projectData.findById(uuid).orElseThrow(() ->
                new NotCorrectUUIDException("Проект не найден"));
    }
    @Transactional
    @Override
    @Audit(action = AuditType.UPDATED_PROJECT, type = EssenceType.PROJECT)
    public UUID update(ProjectCreateDTO dto, UUID uuid, LocalDateTime dt_update) {
        ProjectEntity entity = projectData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        if (!entity.getDtUpdate().equals(dt_update)){
            throw new DtUpdateNotCorrectException("Этот обьект уже кто-то обновил , обновите страницу и повторите попытку!");
        }
        entity.setName(dto.getName());
        entity.setDiscription(dto.getDiscription());
        entity.setManager(dto.getManager().getUuid());
        Set<UUID> list = new HashSet<>();
        for (UserRef userRef:dto.getStaff()){
            list.add(userRef.getUuid());
        }
        entity.setStaff(list);
        entity.setStatus(ProjectStatus.valueOf(dto.getStatus()));
        projectData.save(entity);
        return entity.getUuid();
    }

    @Override
    public List<ProjectEntity> getByUser(UUID uuid) {
        List<ProjectEntity> entities = projectData.findByStaffOrManager(uuid,uuid);

        return entities;
    }

    @Override
    public List<ProjectEntity> getAll() {
        return projectData.findAll();
    }
}
