package by.taskManager.taskservice.core.dto;


import by.taskManager.taskservice.core.serializator.LocalDateTimeSerializer;
import by.taskManager.taskservice.dao.Entity.TaskEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;

public class TaskDTO {
    private String uuid;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dt_create;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dt_update;
    private ProjectRef project;
    private String title;
    private String discription;
    private String status;
    private UserRef implementer;

    public TaskDTO() {
    }

    public TaskDTO(String uuid,
                   LocalDateTime dt_create,
                   LocalDateTime dt_update,
                   ProjectRef project,
                   String title,
                   String discription,
                   String status,
                   UserRef implementer) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.project = project;
        this.title = title;
        this.discription = discription;
        this.status = status;
        this.implementer = implementer;
    }
    public TaskDTO(TaskEntity entity) {
        this.uuid = entity.getUuid().toString();
        this.dt_create = entity.getDtCreate();
        this.dt_update = entity.getDtUpdate();
        this.project = entity.getProject();
        this.title = entity.getTitle();
        this.discription = entity.getDiscription();
        this.status = entity.getStatus().toString();
        this.implementer = entity.getImplementer();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDt_create() {
        return dt_create;
    }

    public void setDt_create(LocalDateTime dt_create) {
        this.dt_create = dt_create;
    }

    public LocalDateTime getDt_update() {
        return dt_update;
    }

    public void setDt_update(LocalDateTime dt_update) {
        this.dt_update = dt_update;
    }

    public ProjectRef getProject() {
        return project;
    }

    public void setProject(ProjectRef project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserRef getImplementer() {
        return implementer;
    }

    public void setImplementer(UserRef implementer) {
        this.implementer = implementer;
    }
}
