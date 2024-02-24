package org.bear.comp.task.api.repository;

import org.bear.comp.task.api.model.Project;
import org.bear.comp.task.api.model.ProjectInvolve;
import org.bear.comp.task.api.model.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectInvolveRepository extends JpaRepository<ProjectInvolve, Long> {

    Optional<ProjectInvolve> findByUuid(UUID uuid);
    Page<ProjectInvolve> findAllByProject(Project project, Pageable pageable);
    Page<ProjectInvolve> findAllByUser(UserProfile user, Pageable pageable);

    @Query(value = "SELECT 1 FROM project_user_roles pur WHERE pur.user_id = :userId and pur.project_id = :projectId",
            nativeQuery = true)
    List<Integer> existByProjectAndUserIds(long userId, long projectId);
}
