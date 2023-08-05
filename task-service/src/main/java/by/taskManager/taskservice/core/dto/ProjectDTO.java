package by.taskManager.taskservice.core.dto;

import by.taskManager.taskservice.core.serializator.LocalDateTimeSerializer;
import by.taskManager.taskservice.dao.entity.ProjectEntity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.List;

public class ProjectDTO {
    private String uuid;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dt_create;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dt_update;
    private String name;
    private String discription;
    private UserRef manager;
    private List<UserRef> staff;

    public ProjectDTO() {
    }

    public ProjectDTO(String uuid,
                      LocalDateTime dt_create,
                      LocalDateTime dt_update,
                      String name,
                      String discription,
                      UserRef manager,
                      List<UserRef> staff) {
        this.uuid = uuid;
        this.dt_create = dt_create;
        this.dt_update = dt_update;
        this.name = name;
        this.discription = discription;
        this.manager = manager;
        this.staff = staff;
    }

    public ProjectDTO(ProjectEntity entity) {
        this.uuid = entity.getUuid().toString();
        this.dt_create = entity.getDtCreate();
        this.dt_update = entity.getDtUpdate();
        this.name = entity.getName();
        this.discription = entity.getDiscription();
        this.manager = entity.getManager();
        this.staff = entity.getStaff();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public UserRef getManager() {
        return manager;
    }

    public void setManager(UserRef manager) {
        this.manager = manager;
    }

    public List<UserRef> getStaff() {
        return staff;
    }

    public void setStaff(List<UserRef> staff) {
        this.staff = staff;
    }
}
