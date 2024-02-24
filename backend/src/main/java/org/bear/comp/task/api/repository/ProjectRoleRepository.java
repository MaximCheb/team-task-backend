package org.bear.comp.task.api.repository;

import org.bear.comp.task.api.model.ProjectRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjectRoleRepository extends CrudRepository<ProjectRole, Long> {
    List<ProjectRole> findAllByProjectId(Long projectId);
    Optional<ProjectRole> findByUuid(UUID uuid);
}
