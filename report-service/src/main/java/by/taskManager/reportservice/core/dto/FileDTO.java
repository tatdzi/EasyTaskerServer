package by.taskManager.reportservice.core.dto;

public class FileDTO {
    private String fileName;
    private byte[] content;

    public FileDTO() {
    }

    public FileDTO(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
