package by.taskManager.taskservice.aop.aspect;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.EssenceType;
import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.taskManager.taskservice.service.api.IAuditService;
import by.taskManager.taskservice.service.component.UserHolder;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
public class AuditAspect {
    private IAuditService auditService;

    private UserHolder userHolder;

    public AuditAspect(IAuditService auditService, UserHolder userHolder) {
        this.auditService = auditService;
        this.userHolder = userHolder;
    }

    @AfterReturning(value = "execution(* by.taskManager.taskservice.service.ProjectService.save(..))",
            returning = "reterning")
    public void afterSaveProjectAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("created new project");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
            auditService.saveItem(audit);

    }
    @AfterReturning(value = "execution(* by.taskManager.taskservice.service.TaskService.save(..))",
            returning = "reterning")
    public void afterSaveTaskAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("created new task");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        auditService.saveItem(audit);

    }
    @AfterReturning(value = "execution(* by.taskManager.taskservice.service.ProjectService.update(..))",
            returning = "reterning")
    public void afterUpdateProjectAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("updated project");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        auditService.saveItem(audit);
    }
    @AfterReturning(value = "execution(* by.taskManager.taskservice.service.TaskService.update(..))",
            returning = "reterning")
    public void afterUpdateTaskAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("updated task");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        auditService.saveItem(audit);
    }
    @AfterReturning(value = "execution(* by.taskManager.taskservice.service.TaskService.updateСondition(..))",
            returning = "reterning")
    public void afterUpdateTaskStatusAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("updated status task");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        auditService.saveItem(audit);
    }
}


