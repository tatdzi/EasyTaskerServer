package by.taskManager.notificationservice.core.dto;

import java.util.UUID;

public class MailDetails {
    private String mailTo;
    private UUID code;

    public MailDetails(String mailTo, UUID code) {
        this.mailTo = mailTo;
        this.code = code;
    }

    public String getMailTo() {
        return mailTo;
    }

    public UUID getCode() {
        return code;
    }
}
