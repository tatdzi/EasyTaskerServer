package by.taskManager.user_service.service;

import by.TaskManeger.utils.dto.MailDetails;
import by.taskManager.user_service.core.exception.ErrorConnectionToService;
import by.taskManager.user_service.endpoints.service.controller.IFeignClientNatification;
import by.taskManager.user_service.service.api.INatificationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FeignNatificationService implements INatificationService {
    private IFeignClientNatification feignClientNatification;

    public FeignNatificationService(IFeignClientNatification feignClientNatification) {
        this.feignClientNatification = feignClientNatification;
    }

    @Override
    public void sendLetter(MailDetails mail) {
        HttpStatus status = feignClientNatification.sendLetter(mail);
        if (!status.equals(HttpStatus.OK)){
            throw new ErrorConnectionToService("Ошибка отправки ферификационного письма!");
        }
    }
}
