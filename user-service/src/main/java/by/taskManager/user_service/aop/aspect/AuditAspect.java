package by.taskManager.user_service.aop.aspect;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.EssenceType;
import by.taskManager.user_service.endpoints.service.controller.IFeignClientAudit;
import by.taskManager.user_service.service.component.UserHolder;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
public class AuditAspect {
    private IFeignClientAudit feignClientAudit;
    private UserHolder userHolder;

    public AuditAspect(IFeignClientAudit feignClientAudit,UserHolder userHolder) {
        this.feignClientAudit = feignClientAudit;
        this.userHolder = userHolder;
    }

    @AfterReturning(value = "execution(void by.taskManager.user_service.service.UserService.save(..))",
            returning = "reterning")
    public void afterSaveAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("created new user");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        feignClientAudit.saveItem(audit);
    }
    @AfterReturning(value = "execution(void by.taskManager.user_service.service.UserService.upadte(..))",
            returning = "reterning")
    public void afterUpdateAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("updated new user");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        feignClientAudit.saveItem(audit);
    }
    @AfterReturning(value = "execution(void by.taskManager.user_service.service.AuthService.save(..))",
            returning = "reterning")
    public void afterRegistrationAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("registrated new user");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        feignClientAudit.saveItem(audit);
    }
    @AfterReturning(value = "execution(void by.taskManager.user_service.service.AuthService.auth(..))",
            returning = "reterning")
    public void afterVerificationAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        audit.setUser(userHolder.getUser());
        audit.setText("user was verificate");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        feignClientAudit.saveItem(audit);
    }
}


