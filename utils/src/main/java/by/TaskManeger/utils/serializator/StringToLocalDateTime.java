package by.TaskManeger.utils.serializator;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class StringToLocalDateTime implements Converter <String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String time1) {
        Long time  = Long.valueOf(time1);
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneOffset.UTC);
    }
}
