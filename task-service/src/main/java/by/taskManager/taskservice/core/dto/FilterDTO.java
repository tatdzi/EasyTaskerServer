package by.taskManager.taskservice.core.dto;

import java.util.List;
import java.util.UUID;

public class FilterDTO {
    private List<ProjectRef> projects;
    private List<UserRef> implementers;
    private List<TaskStatus> status;


    public FilterDTO(List<ProjectRef> projects,
                     List<UserRef> implementers,
                     List<TaskStatus> status) {
        this.projects = projects;
        this.implementers = implementers;
        this.status = status;
    }

    public List<ProjectRef> getProjects() {
        return projects;
    }

    public List<UserRef> getImplementers() {
        return implementers;
    }

    public List<TaskStatus> getStatus() {
        return status;
    }
}
