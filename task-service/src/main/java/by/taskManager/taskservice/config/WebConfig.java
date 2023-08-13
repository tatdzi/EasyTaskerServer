package by.taskManager.taskservice.config;


import by.taskManager.taskservice.core.converter.StringToProjectRefConverter;
import by.taskManager.taskservice.core.converter.StringToUserRefConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToProjectRefConverter());
        registry.addConverter(new StringToUserRefConverter());
    }
}
