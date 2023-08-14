package by.taskManager.taskservice.dao.api;

import by.taskManager.taskservice.dao.entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface IProjectData extends JpaRepository<ProjectEntity, UUID>,
        PagingAndSortingRepository<ProjectEntity, UUID> {
    Page<ProjectEntity> findByStatusIs(Pageable pageable,String status);
    List<ProjectEntity> findByStaffOrManager(UUID uuid,UUID uuid1);
    List<ProjectEntity> findAll();
}
