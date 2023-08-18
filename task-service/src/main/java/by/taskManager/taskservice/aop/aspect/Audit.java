package by.taskManager.taskservice.aop.aspect;

import by.TaskManeger.utils.dto.EssenceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {
    AuditType action();
    EssenceType type();
}
