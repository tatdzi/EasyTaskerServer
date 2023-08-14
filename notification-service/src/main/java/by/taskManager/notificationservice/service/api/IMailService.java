package by.taskManager.notificationservice.service.api;

import by.TaskManeger.utils.dto.MailDetails;

public interface IMailService {
    void sendVerificationLetter(MailDetails mailDetails);
}
