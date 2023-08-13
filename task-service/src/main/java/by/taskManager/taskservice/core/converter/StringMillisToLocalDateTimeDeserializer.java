package by.taskManager.taskservice.core.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class StringMillisToLocalDateTimeDeserializer implements Converter<Long , LocalDateTime> {
    @Override
    public LocalDateTime convert(Long source) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(source), ZoneOffset.UTC);
    }

}
