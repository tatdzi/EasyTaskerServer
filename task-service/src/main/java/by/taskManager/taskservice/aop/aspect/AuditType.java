package by.taskManager.taskservice.aop.aspect;


public enum AuditType {
    CREATE_PROJECT("Created new project"),
    UPDATED_PROJECT("Updated project"),
    CREATE_TASK("`Create new task"),
    UPDATE_TASK("Update task"),
    UPDATE_STATUS_OF_TASK("Update status of task");

    private final String description;

    AuditType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
