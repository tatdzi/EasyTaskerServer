package by.taskManager.taskservice.core.converter;

import by.taskManager.taskservice.core.dto.ProjectRef;
import by.taskManager.taskservice.core.dto.UserRef;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

public class StringToUserRefConverter implements Converter<String, UserRef> {
    @Override
    public UserRef convert(String source) {
        return new UserRef(UUID.fromString(source));
    }
}
