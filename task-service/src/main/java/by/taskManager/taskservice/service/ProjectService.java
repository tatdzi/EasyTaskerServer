package by.taskManager.taskservice.service;

import by.TaskManeger.utils.dto.PageDTO;
import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.ProjectDTO;
import by.taskManager.taskservice.core.dto.ProjectStatus;
import by.taskManager.taskservice.core.dto.UserRef;
import by.taskManager.taskservice.core.exception.DtUpdateNotCorrectException;
import by.taskManager.taskservice.core.exception.NotCorrectUUIDException;
import by.taskManager.taskservice.dao.entity.ProjectEntity;
import by.taskManager.taskservice.dao.api.IProjectData;
import by.taskManager.taskservice.dao.entity.UserEntity;
import by.taskManager.taskservice.service.api.IProjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
public class ProjectService implements IProjectService {
    private IProjectData projectData;
    private String ACTIVE = "ACTIVE";

    public ProjectService(IProjectData projectData) {
        this.projectData = projectData;
    }

    @Override
    public UUID save(ProjectCreateDTO dto) {
        ProjectEntity entity = new ProjectEntity(dto);
        entity.setUuid(UUID.randomUUID());
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
        Page<ProjectEntity> pageResponse = projectData.findByStatusIs(PageRequest.of(page, size),ACTIVE);
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
    public UUID upadte(ProjectCreateDTO dto, UUID uuid, LocalDateTime dt_update) {
        ProjectEntity entity = projectData.findById(uuid).orElseThrow(()->new IllegalArgumentException("не нашел"));
        if (!entity.getDtUpdate().equals(dt_update)){
            throw new DtUpdateNotCorrectException("Этот обьект уже кто-то обновил , обновите страницу и повторите попытку!");
        }
        entity.setName(dto.getName());
        entity.setDiscription(dto.getDiscription());
        entity.setManager(new UserEntity(dto.getManager().getUuid()));
        List<UserEntity> list = new ArrayList<>();
        for (UserRef userRef:dto.getStaff()){
            list.add(new UserEntity(userRef.getUuid()));
        }
        entity.setStaff(list);
        entity.setStatus(ProjectStatus.valueOf(dto.getStatus()));
        projectData.save(entity);
        return entity.getUuid();
    }
}
