package by.TaskManeger.utils.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class MailDetails {
    private String mailTo;
    private UUID code;

    public MailDetails() {
    }

    public MailDetails(String mailTo, UUID code) {
        this.mailTo = mailTo;
        this.code = code;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public void setCode(UUID code) {
        this.code = code;
    }

    public String getMailTo() {
        return mailTo;
    }

    public UUID getCode() {
        return code;
    }
}
