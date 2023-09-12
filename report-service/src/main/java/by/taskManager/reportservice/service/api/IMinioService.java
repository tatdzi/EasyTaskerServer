package by.taskManager.reportservice.service.api;

import by.taskManager.reportservice.core.dto.FileDTO;

import java.io.File;

public interface IMinioService {
    String upload(File report);
    FileDTO download(String fileName);
}
