package by.taskManager.user_service.service.api;

import by.TaskManeger.utils.dto.MailDetails;

public interface INotificationService {
    void sendLetter(MailDetails mail);
}
