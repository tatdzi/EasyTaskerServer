package by.taskManager.taskservice.config.property;

import by.taskManager.taskservice.endpoint.service.controller.fallback.RetreiveMessageErrorDecoder;
import org.springframework.context.annotation.Bean;
import feign.codec.ErrorDecoder;
public class FeignConfig {

    @Bean
    ErrorDecoder errorDecoder() {
        return new RetreiveMessageErrorDecoder();
    }
}
