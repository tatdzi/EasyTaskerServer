package by.taskManager.taskservice.dao.specification;

import by.taskManager.taskservice.core.dto.ProjectRef;
import by.taskManager.taskservice.core.dto.TaskStatus;
import by.taskManager.taskservice.core.dto.UserRef;
import by.taskManager.taskservice.dao.entity.TaskEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public class TaskSpecs {
    public static Specification<TaskEntity> equalsProject(List<ProjectRef> projects){
        List<UUID> list = projects.stream().map(ProjectRef::getUuid).toList();
        return (root, query, builder) ->
           builder.in(root.get("project").get("uuid")).value(list);

    }
    public static Specification<TaskEntity> equalsImplementer(List<UserRef> implementers){
        List<UUID> list = implementers.stream().map(UserRef::getUuid).toList();
        return (root, query, builder) ->
            builder.in(root.get("implementer")).value(list);

    }
    public static Specification<TaskEntity> equalsStatus(List<TaskStatus> statuses){
        return (root, query, builder) ->
            builder.in(root.get("status")).value(statuses);

    }
}
