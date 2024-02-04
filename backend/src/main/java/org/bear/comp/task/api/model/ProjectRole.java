package org.bear.comp.task.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table( schema = "project", name = "project_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"projectId", "code", "active"})})
public class ProjectRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    private Date createDate;

    private Boolean active;

    private Long projectId;

    private String code;

    private String name;

    private long prevRole;
}
