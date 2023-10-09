package by.taskManager.reportservice.dao.api;

import by.taskManager.reportservice.core.dto.ReportStatus;
import by.taskManager.reportservice.dao.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface IReportData extends JpaRepository<Report,UUID>, PagingAndSortingRepository<Report, UUID> {
    List<Report> findByStatus(ReportStatus status);
}
