package by.taskManager.reportservice.dao.entity;


import by.taskManager.reportservice.core.dto.ReportStatus;
import by.taskManager.reportservice.core.dto.ReportType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    private UUID uuid;
    @CreationTimestamp
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @UpdateTimestamp
    @Version
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReportStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ReportType type;
    @Column(name = "description", nullable = false)
    private String description;
    @Type(JsonType.class)
    @Column(name = "param", nullable = false)
    private Map<String, Object> param;

    public Report() {
    }

    public Report(UUID uuid, ReportStatus status, ReportType type, String description, Map<String, Object> param) {
        this.uuid = uuid;
        this.status = status;
        this.type = type;
        this.description = description;
        this.param = param;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public Map<String, Object> getParam() {
        return param;
    }

    public void setParam(Map<String, Object> param) {
        this.param = param;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (getUuid() != null ? !getUuid().equals(report.getUuid()) : report.getUuid() != null) return false;
        if (getStatus() != report.getStatus()) return false;
        if (getType() != report.getType()) return false;
        if (getDescription() != null ? !getDescription().equals(report.getDescription()) : report.getDescription() != null)
            return false;
        if (getParam() != null ? !getParam().equals(report.getParam()) : report.getParam() != null) return false;
        if (getCreateDate() != null ? !getCreateDate().equals(report.getCreateDate()) : report.getCreateDate() != null)
            return false;
        return getUpdateDate() != null ? getUpdateDate().equals(report.getUpdateDate()) : report.getUpdateDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getUuid() != null ? getUuid().hashCode() : 0;
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getParam() != null ? getParam().hashCode() : 0);
        result = 31 * result + (getCreateDate() != null ? getCreateDate().hashCode() : 0);
        result = 31 * result + (getUpdateDate() != null ? getUpdateDate().hashCode() : 0);
        return result;
    }
}
