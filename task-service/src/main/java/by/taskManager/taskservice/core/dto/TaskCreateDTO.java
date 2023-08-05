package by.taskManager.taskservice.core.dto;

public class TaskCreateDTO {
    private ProjectRef project;
    private String title;
    private String discription;
    private String status;
    private UserRef implementer;

    public TaskCreateDTO() {
    }

    public TaskCreateDTO(ProjectRef project, String title, String discription, String status, UserRef implementer) {
        this.project = project;
        this.title = title;
        this.discription = discription;
        this.status = status;
        this.implementer = implementer;
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
