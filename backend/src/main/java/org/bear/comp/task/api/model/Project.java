package org.bear.comp.task.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(schema = "project", name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    private Date createDate;

    private Boolean active;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, unique = true, length = 16)
    private String initials;

    @Column(nullable = false)
    private String description;
}
