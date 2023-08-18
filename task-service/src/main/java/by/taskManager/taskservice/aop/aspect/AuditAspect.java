package by.taskManager.taskservice.aop.aspect;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.EssenceType;
import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.taskManager.taskservice.service.api.IAuditService;
import by.taskManager.taskservice.service.component.UserHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
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

    @AfterReturning(value = "@annotation(Audit)",
            returning = "reterning")
    public void afterSaveProjectAdvice(JoinPoint joinPoint, UUID reterning){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Audit type = method.getAnnotation(Audit.class);
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText(type.action().getDescription());
        audit.setType(type.type());
        audit.setId(reterning.toString());
            auditService.saveItem(audit);
    }
}


