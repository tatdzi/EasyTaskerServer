package by.taskManager.taskservice.dao.entity;

import by.taskManager.taskservice.core.dto.ProjectRef;
import by.taskManager.taskservice.core.dto.TaskCreateDTO;
import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.core.dto.UserRef;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    private UUID uuid;
    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;
    private ProjectRef project;
    private String title;

    private String discription;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
    private UserRef implementer;

    public TaskEntity() {
    }

    public TaskEntity(UUID uuid,
                      ProjectRef project,
                      String title,
                      String discription,
                      TaskStatus status,
                      UserRef implementer) {
        this.uuid = uuid;
        this.project = project;
        this.title = title;
        this.discription = discription;
        this.status = status;
        this.implementer = implementer;
    }
    public TaskEntity(TaskCreateDTO dto) {
        this.project = dto.getProject();
        this.title = dto.getTitle();
        this.discription = dto.getDiscription();
        this.status = TaskStatus.valueOf(dto.getStatus());
        this.implementer = dto.getImplementer();
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public UserRef getImplementer() {
        return implementer;
    }

    public void setImplementer(UserRef implementer) {
        this.implementer = implementer;
    }
}
