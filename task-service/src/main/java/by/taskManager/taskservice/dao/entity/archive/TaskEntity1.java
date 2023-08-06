package by.taskManager.taskservice.dao.entity.archive;

import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.dao.entity.ProjectEntity;
import by.taskManager.taskservice.dao.entity.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


//@Table(name = "task")
public class TaskEntity1 {
    //@Id
    private UUID uuid;
    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    @ManyToOne
    @JoinColumn(name = "project_uuid")
    private by.taskManager.taskservice.dao.entity.ProjectEntity project;
    private String title;

    private String discription;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UserEntity implementer;

    public TaskEntity1() {
    }

    public TaskEntity1(UUID uuid,
                       by.taskManager.taskservice.dao.entity.ProjectEntity project,
                       String title,
                       String discription,
                       TaskStatus status,
                       UserEntity implementer) {
        this.uuid = uuid;
        this.project = project;
        this.title = title;
        this.discription = discription;
        this.status = status;
        this.implementer = implementer;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public by.taskManager.taskservice.dao.entity.ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserEntity getImplementer() {
        return implementer;
    }

    public void setImplementer(UserEntity implementer) {
        this.implementer = implementer;
    }
}
