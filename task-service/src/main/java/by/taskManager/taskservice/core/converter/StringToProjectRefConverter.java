package by.taskManager.taskservice.core.converter;

import org.springframework.core.convert.converter.Converter;
import by.taskManager.taskservice.core.dto.ProjectRef;

import java.util.UUID;

public class StringToProjectRefConverter implements Converter<String, ProjectRef> {
    @Override
    public ProjectRef convert(String source) {
        return new ProjectRef(UUID.fromString(source));
    }
}
