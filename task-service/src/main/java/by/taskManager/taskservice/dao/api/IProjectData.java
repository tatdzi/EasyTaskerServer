package by.taskManager.taskservice.dao.api;

import by.taskManager.taskservice.dao.Entity.ProjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface IProjectData extends CrudRepository<ProjectEntity, UUID>,
        PagingAndSortingRepository<ProjectEntity, UUID> {
    Page<ProjectEntity> findByStatusIs(Pageable pageable,String status);
}
