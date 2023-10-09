package by.taskManager.reportservice.dao.api;

import by.taskManager.reportservice.core.dto.FormatType;
import org.springframework.stereotype.Repository;

import java.io.File;

public interface IFileData {

    void upload(File file, String bucket);
    String getLink(String name, String bucket);
}
