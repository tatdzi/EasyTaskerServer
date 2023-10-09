package by.taskManager.reportservice.dao;

import by.taskManager.reportservice.dao.api.IFileData;
import io.minio.*;
import io.minio.http.Method;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
@Repository
public class MinioData implements IFileData {
    private final MinioClient minioClient;

    public MinioData(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Override
    public void upload(File file, String bucket) {
        this.validation(file,bucket);
        try (InputStream inputStream = new FileInputStream(file)) {
            this.minioClient.putObject(PutObjectArgs.builder()
                    .stream(inputStream, inputStream.available(), -1)
                    .bucket(bucket)
                    .object(file.getName())
                    .build());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String getLink(String name, String bucket) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                                    .method(Method.GET)
                                    .bucket(bucket)
                                    .object(name)
                                    .expiry(5, TimeUnit.MINUTES)
                                    .build());
        } catch (Exception e) {
            throw new RuntimeException("error");
        }
    }
    private void validation(File file, String bucket){
        try {
            if(!this.minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucket).build())){
                this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
            }
        } catch (Exception e){
            throw new RuntimeException("error creating of bucket");
        }
        if(!file.exists()){
            throw new RuntimeException("file not exists");
        }
    }
}
