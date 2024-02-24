package org.bear.comp.task.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Entity
@Table( schema = "users", name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private UUID uuid;

    @Column(nullable = false)
    private Date createDate;

    private Boolean active;

    @Column(nullable = false, unique = true, length = 64)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "firstname", length = 128)
    private String firstName;

    @Column(name = "secondname", length = 128)
    private String secondName;

    @Column(unique = true, length = 64)
    private String email;

    @Column(unique = true, length = 64)
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            schema = "users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<SystemRole> userRoles;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userProfile")
//    private Set<UserRole> systemRoles;

}