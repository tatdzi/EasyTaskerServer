package by.taskManager.taskservice.dao.entity.archive;

import by.taskManager.taskservice.core.dto.ProjectCreateDTO;
import by.taskManager.taskservice.core.dto.ProjectStatus;
import by.taskManager.taskservice.core.dto.UserRef;
import by.taskManager.taskservice.dao.entity.UserEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


//@Table(name = "project")
public class ProjectEntity1 {
    //@Id
    @Column(name = "project_uuid")
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
    @ManyToOne
    @JoinColumn(name = "user_uuid")
    private UserEntity manager;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "project_staff",
            joinColumns = @JoinColumn(name = "project_uuids",referencedColumnName="project_uuid"),
            inverseJoinColumns = @JoinColumn(name = "user_uuids",referencedColumnName="user_uuid"))
    private List<UserEntity> staff;
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public ProjectEntity1() {
    }

    public ProjectEntity1(UUID uuid, String name, String discription, UserEntity manager, List<UserEntity> staff, ProjectStatus status) {
        this.uuid = uuid;
        this.name = name;
        this.discription = discription;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
    }
    public ProjectEntity1(ProjectCreateDTO dto) {
        this.name = dto.getName();
        this.discription = dto.getDiscription();
        this.manager = new UserEntity(dto.getManager().getUuid());
        List<UserEntity> list = new ArrayList<>();
        for (UserRef userRef:dto.getStaff()){
            list.add(new UserEntity(userRef.getUuid()));
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

    public UserEntity getManager() {
        return manager;
    }

    public void setManager(UserEntity manager) {
        this.manager = manager;
    }

    public List<UserEntity> getStaff() {
        return staff;
    }

    public void setStaff(List<UserEntity> staff) {
        this.staff = staff;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
