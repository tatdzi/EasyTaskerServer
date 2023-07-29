package by.taskManager.notificationservice.service;

import by.taskManager.notificationservice.core.dto.MailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${verification.url}")
    private String url;


    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationLetter(MailDetails mailDetails){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        String message = String.format(
                "Welcome to Task Messager. Please, visit next link: " + url+"?code=" +
                        mailDetails.getCode()+"&mail="+mailDetails.getMailTo());
        mailMessage.setFrom(sender);
        mailMessage.setTo(mailDetails.getMailTo());
        mailMessage.setSubject("Verification code");
        mailMessage.setText(message);

        mailSender.send(mailMessage);

    }
}
