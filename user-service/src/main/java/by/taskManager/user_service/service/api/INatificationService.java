package by.taskManager.user_service.service.api;

import by.TaskManeger.utils.dto.MailDetails;

public interface INatificationService {
    void sendLetter(MailDetails mail);
}
