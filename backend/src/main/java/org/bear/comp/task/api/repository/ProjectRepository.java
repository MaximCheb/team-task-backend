package org.bear.comp.task.api.repository;

import org.bear.comp.task.api.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findAll(Pageable pageable);
    Optional<Project> findByUuid(UUID uuid);
    Optional<Project> findByName(String name);
}
