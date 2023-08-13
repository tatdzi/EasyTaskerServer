package by.taskManager.taskservice.core.dto;

import java.util.List;

public class ProjectCreateDTO {
    private String name;
    private String discription;
    private UserRef manager;
    private List<UserRef> staff;
    private String status;

    public ProjectCreateDTO() {
    }

    public ProjectCreateDTO(String name, String discription, UserRef manager, List<UserRef> staff, String status) {
        this.name = name;
        this.discription = discription;
        this.manager = manager;
        this.staff = staff;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
