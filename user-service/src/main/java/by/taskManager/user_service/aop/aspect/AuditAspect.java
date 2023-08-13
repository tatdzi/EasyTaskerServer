package by.taskManager.user_service.aop.aspect;

import by.TaskManeger.utils.dto.AuditDTO;
import by.TaskManeger.utils.dto.EssenceType;
import by.TaskManeger.utils.dto.TokenDTO;
import by.TaskManeger.utils.dto.UserRole;
import by.taskManager.user_service.service.api.IAuditService;
import by.taskManager.user_service.service.component.UserHolder;
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

    public AuditAspect(IAuditService auditService,UserHolder userHolder) {
        this.auditService = auditService;
        this.userHolder = userHolder;
    }

    @AfterReturning(value = "execution(* by.taskManager.user_service.service.UserService.save(..))",
            returning = "reterning")
    public void afterSaveAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        try {
            TokenDTO r = userHolder.getUser();
            audit.setUser(userHolder.getUser());
        }catch (RuntimeException e){
            TokenDTO tokenDTO = new TokenDTO();
            //todo что делать если регистрация происходит без авторизации
            tokenDTO.setUuid(reterning);
            tokenDTO.setRole(UserRole.USER);
            tokenDTO.setFio("new user");
            tokenDTO.setMail("@");
            audit.setUser(tokenDTO);
        }

        audit.setText("created new user");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
            auditService.saveItem(audit);

    }
    @AfterReturning(value = "execution(* by.taskManager.user_service.service.UserService.update(..))",
            returning = "reterning")
    public void afterUpdateAdvice(UUID reterning){
        AuditDTO audit = new AuditDTO();
        audit.setUuid(UUID.randomUUID());
        audit.setDtCreate(LocalDateTime.now());
        try {
            TokenDTO r = userHolder.getUser();
            audit.setUser(userHolder.getUser());
        }catch (RuntimeException e){
            TokenDTO tokenDTO = new TokenDTO();
            //todo что делать если регистрация происходит без авторизации
            tokenDTO.setUuid(reterning);
            tokenDTO.setRole(UserRole.USER);
            tokenDTO.setFio("new user");
            tokenDTO.setMail("@");
            audit.setUser(tokenDTO);
        }
        audit.setText("updated new user");
        audit.setType(EssenceType.USER);
        audit.setId(reterning.toString());
        auditService.saveItem(audit);
    }
}


