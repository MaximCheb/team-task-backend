package org.bear.comp.task.api.repository;

import org.bear.comp.task.api.model.SystemRole;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface SystemRoleRepository extends CrudRepository<SystemRole, Long> {

    Optional<SystemRole>findByUuid(UUID uuid);
    Optional<SystemRole>findByName(String name);
    Optional<SystemRole>findByCode(String code);
}
