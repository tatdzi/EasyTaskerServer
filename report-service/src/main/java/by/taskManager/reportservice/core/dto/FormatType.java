package by.taskManager.reportservice.core.dto;

public enum FormatType {
    XLSX(".xlsx");
    private String value;
    FormatType(String value) {
        this.value=value;
    }
    public String getValue() {
        return value;
    }
}
