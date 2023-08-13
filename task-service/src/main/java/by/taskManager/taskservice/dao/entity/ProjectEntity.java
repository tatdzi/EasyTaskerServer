package by.taskManager.taskservice.dao.entity;

import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.ProjectStatus;
import by.taskManager.taskservice.core.dto.UserRef;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    private UUID uuid;
    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime dtCreate;

    @Version
    @UpdateTimestamp
    @Column(name = "dt_update")
    private LocalDateTime dtUpdate;

    private String name;
    private String discription;
    private UUID manager;
    @ElementCollection
    @CollectionTable(name="project_staff",
            joinColumns=@JoinColumn(name="project_uuid")
    )
    @Column(name = "staff_uuid")
    private Set<UUID> staff;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public ProjectEntity() {
    }

    public ProjectEntity(UUID uuid, String name, String discription, UUID manager, Set<UUID> staff, ProjectStatus status) {
        this.uuid = uuid;
        this.name = name;
        this.discription = discription;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
    }
    public ProjectEntity(ProjectCreateDTO dto) {
        this.name = dto.getName();
        this.discription = dto.getDiscription();
        this.manager = dto.getManager().getUuid();
        Set<UUID> list = new HashSet<>();
        for (UserRef userRef:dto.getStaff()){
            list.add(userRef.getUuid());
        }
        this.staff = list;
        this.status = ProjectStatus.valueOf(dto.getStatus());
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

    public UUID getManager() {
        return manager;
    }

    public void setManager(UUID manager) {
        this.manager = manager;
    }

    public Set<UUID> getStaff() {
        return staff;
    }

    public void setStaff(Set<UUID> staff) {
        this.staff = staff;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
