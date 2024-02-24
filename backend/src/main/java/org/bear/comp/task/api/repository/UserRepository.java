package org.bear.comp.task.api.repository;

import org.bear.comp.task.api.model.Project;
import org.bear.comp.task.api.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);
    Optional<UserProfile> findByEmail(String email);
    Optional<UserProfile> findByUuid(UUID uuid);
}
