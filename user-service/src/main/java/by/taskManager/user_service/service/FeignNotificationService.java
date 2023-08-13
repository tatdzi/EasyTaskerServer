package by.taskManager.user_service.service;

import by.TaskManeger.utils.dto.MailDetails;
import by.taskManager.user_service.core.exception.ErrorConnectionToService;
import by.taskManager.user_service.endpoints.service.controller.IFeignClientNotification;
import by.taskManager.user_service.service.api.INotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FeignNotificationService implements INotificationService {
    private IFeignClientNotification feignClientNatification;

    public FeignNotificationService(IFeignClientNotification feignClientNatification) {
        this.feignClientNatification = feignClientNatification;
    }

    @Override
    public void sendLetter(MailDetails mail) {
        ResponseEntity<?> status = feignClientNatification.sendLetter(mail);
        if (!status.getStatusCode().equals(HttpStatus.OK)){
            throw new ErrorConnectionToService("Ошибка отправки ферификационного письма!");
        }
    }
}
