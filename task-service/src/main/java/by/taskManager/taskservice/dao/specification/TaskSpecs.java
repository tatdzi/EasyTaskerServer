package by.taskManager.taskservice.dao.specification;

import by.taskManager.taskservice.core.dto.ProjectRef;
import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.core.dto.UserRef;
import by.taskManager.taskservice.dao.Entity.TaskEntity;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Selection;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class TaskSpecs {
    public static Specification<TaskEntity> equalsProject(List<ProjectRef> projects){
        return (root, query, builder) -> {
          return builder.in(root.get("project")).value(projects);
        };
    }
    public static Specification<TaskEntity> equalsImplementer(List<UserRef> implementers){
        return (root, query, builder) -> {
            return builder.in(root.get("implementer")).value(implementers);
        };
    }
    public static Specification<TaskEntity> equalsStatus(List<TaskStatus> statuses){
        return (root, query, builder) -> {
            return builder.in(root.get("status")).value(statuses);
        };
    }
}
