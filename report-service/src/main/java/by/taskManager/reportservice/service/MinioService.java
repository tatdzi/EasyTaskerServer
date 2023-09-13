package by.taskManager.reportservice.service;

import by.taskManager.reportservice.config.property.MinioProperties;
import by.taskManager.reportservice.core.dto.FileDTO;
import by.taskManager.reportservice.service.api.IMinioService;
import io.minio.*;
import org.apache.commons.compress.utils.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class MinioService implements IMinioService {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public MinioService(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }
    @Override
    public String upload(File report){
        try {
            createBucket();
        } catch (Exception e){
            throw new RuntimeException();
        }
        if(!report.exists()){
            throw new RuntimeException();
        }
        String fileName = report.getName();
        try (InputStream inputStream = new FileInputStream(report)) {
            this.saveReport(inputStream, fileName);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return fileName;
    }
    @Override
    public FileDTO download(String fileName){
        try {
            GetObjectArgs args = GetObjectArgs.builder()
                    .bucket(this.minioProperties.getBucket())
                    .object(fileName)
                    .build();
            InputStream is = this.minioClient.getObject(args);
            byte[] content = IOUtils.toByteArray(is);
            is.close();
            return new FileDTO(fileName, content);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    private void createBucket() throws Exception {
        boolean found = this.minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(this.minioProperties.getBucket())
                .build());
        if(!found){
            this.minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(this.minioProperties.getBucket())
                    .build());
        }
    }
    private void saveReport(InputStream inputStream, String filename) throws Exception {
        this.minioClient.putObject(PutObjectArgs.builder()
                .stream(inputStream, inputStream.available(), -1)
                .bucket(this.minioProperties.getBucket())
                .object(filename)
                .build());
    }
}