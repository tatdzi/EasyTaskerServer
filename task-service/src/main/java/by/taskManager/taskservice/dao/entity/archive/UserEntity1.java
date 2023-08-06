package by.taskManager.taskservice.dao.entity.archive;

import jakarta.persistence.Column;

import java.util.UUID;


//@Table(name = "user")
public class UserEntity1 {
    //@Id
    @Column(name = "user_uuid")
    private UUID uuid;

    public UserEntity1() {
    }

    public UserEntity1(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
