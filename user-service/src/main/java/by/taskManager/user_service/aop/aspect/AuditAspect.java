package by.taskManager.user_service.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuditAspect {

    @After("execution(void by.taskManager.user_service.service.UserService.save(" +
            "by.taskManager.user_service.core.dto.UserCreateDTO))")
    public void afterSaveAdvice(JoinPoint joinPoint){

    }
}
