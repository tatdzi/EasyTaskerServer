package by.taskManager.reportservice.dao.api;

import by.taskManager.reportservice.dao.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IReportData extends JpaRepository<Report,UUID>, PagingAndSortingRepository<Report, UUID> {
}
