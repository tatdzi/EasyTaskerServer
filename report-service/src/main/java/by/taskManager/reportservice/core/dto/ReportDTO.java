package by.taskManager.reportservice.core.dto;

import by.TaskManeger.utils.serializator.LocalDateTimeSerializer;
import by.TaskManeger.utils.serializator.StringDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReportDTO {
    private UUID uuid;

    @JsonProperty("dt_create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private LocalDateTime dtCreate;
    @JsonProperty("dt_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = StringDeserializer.class)
    private LocalDateTime dtUpdate;
    private ReportStatus status;
    private ReportType type;
    private String description;
    private ReportParamAudit params;

    public ReportDTO() {
    }

    public ReportDTO(UUID uuid,
                     LocalDateTime dtCreate,
                     LocalDateTime dtUpdate,
                     ReportStatus status,
                     ReportType type,
                     String description,
                     ReportParamAudit params) {
        this.uuid = uuid;
        this.dtCreate = dtCreate;
        this.dtUpdate = dtUpdate;
        this.status = status;
        this.type = type;
        this.description = description;
        this.params = params;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getDtCreate() {
        return dtCreate;
    }

    public void setDtCreate(LocalDateTime dtCreate) {
        this.dtCreate = dtCreate;
    }

    public LocalDateTime getDtUpdate() {
        return dtUpdate;
    }

    public void setDtUpdate(LocalDateTime dtUpdate) {
        this.dtUpdate = dtUpdate;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ReportParamAudit getParams() {
        return params;
    }

    public void setParams(ReportParamAudit params) {
        this.params = params;
    }
}
