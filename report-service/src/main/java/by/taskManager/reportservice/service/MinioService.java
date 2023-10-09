package by.taskManager.reportservice.service;

import by.taskManager.reportservice.config.property.MinioProperties;
import by.taskManager.reportservice.dao.api.IFileData;
import by.taskManager.reportservice.service.api.IMinioService;
import org.springframework.stereotype.Service;
import java.io.File;

@Service

public class MinioService implements IMinioService {
    private MinioProperties minioProperties;
    private IFileData fileData;

    public MinioService(MinioProperties minioProperties, IFileData fileData) {
        this.minioProperties = minioProperties;
        this.fileData = fileData;
    }

    @Override
    public String upload(File report) {
        fileData.upload(report,minioProperties.getBucket());
        return report.getName();
    }

    @Override
    public String download(String fileName) {
        return fileData.getLink(fileName,minioProperties.getBucket());
    }
}
