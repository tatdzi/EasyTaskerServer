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
@Table(name = "report")
public class Report {
    @Id
    private UUID uuid;
    @CreationTimestamp
    @Column(name = "dt_create")
    private LocalDateTime createDate;
    @UpdateTimestamp
    @Version
    @Column(name = "dt_update")
    private LocalDateTime updateDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ReportType type;
    @Column(name = "discription")
    private String discription;
    @Type(JsonType.class)
    @Column(name = "param")
    private Map<String, String> param;

    public Report() {
    }

    public Report(UUID uuid, ReportStatus status, ReportType type, String discription, Map<String, String> param) {
        this.uuid = uuid;
        this.status = status;
        this.type = type;
        this.discription = discription;
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
        return discription;
    }

    public void setDiscription(String description) {
        this.discription = description;
    }

    public Map<String, String> getParam() {
        return param;
    }

    public void setParam(Map<String, String> param) {
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
