INSERT INTO users.system_role
(id, active, "uuid", create_date, code, "name", prev_role)
VALUES(nextval('users.system_role_id_seq'::regclass), true, gen_random_uuid() , CURRENT_TIMESTAMP, 'user', 'user', null),
(nextval('users.system_role_id_seq'::regclass), true, gen_random_uuid() , CURRENT_TIMESTAMP, 'manager', 'project-manager', 1),
(nextval('users.system_role_id_seq'::regclass), true, gen_random_uuid() , CURRENT_TIMESTAMP, 'admin', 'admin', 1);

CREATE TABLE IF NOT EXISTS users.user_roles
(
    id              BIGSERIAL PRIMARY KEY,
    active          BOOLEAN     DEFAULT true,
    uuid            UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(),
    create_date     TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
    user_id         BIGSERIAL NOT NULL,
    role_id         BIGSERIAL NOT NULL,
    CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users.user_profile(id) ON DELETE CASCADE,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES users.system_role(id) ON DELETE CASCADE,
    CONSTRAINT uq_user_role UNIQUE (user_id, role_id)
);

CREATE TABLE IF NOT EXISTS project.project_role
(
    id              BIGSERIAL PRIMARY KEY,
    active          BOOLEAN     DEFAULT true,
    uuid            UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(),
    create_date     TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
    code            VARCHAR(64) UNIQUE NOT NULL,
    name            VARCHAR(128) UNIQUE NOT NULL,
    prev_role       BIGSERIAL
);

CREATE UNIQUE INDEX idx_project_role_code ON project.project_role USING BTREE (code);

CREATE TABLE IF NOT EXISTS project.project_user_roles
(
    id              BIGSERIAL PRIMARY KEY,
    active          BOOLEAN     DEFAULT true,
    uuid            UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(),
    create_date     TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
    user_id         BIGSERIAL NOT NULL,
    role_id         BIGSERIAL NOT NULL,
    project_id      BIGSERIAL NOT NULL,
    CONSTRAINT fk_project_user_id FOREIGN KEY (user_id) REFERENCES users.user_profile(id) ON DELETE CASCADE,
    CONSTRAINT fk_project_role_id FOREIGN KEY (role_id) REFERENCES users.system_role(id) ON DELETE CASCADE,
    CONSTRAINT fk_project_project_id FOREIGN KEY (project_id) REFERENCES project.project(id) ON DELETE CASCADE,
    CONSTRAINT uq_project_user_roles UNIQUE (user_id, role_id, project_id)
);

INSERT INTO project.project_role
(id, active, "uuid", create_date, code, "name", prev_role) VALUES
    (1, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'viewer', 'project viewer', null),
    (2, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'editor', 'project editor', 1),
    (3, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'tester', 'software tester', 2),
    (4, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'designer', 'ui/ux designer', 2),
    (5, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'writer', 'project writer', 2),
    (6, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'developer', 'software developer', 2),
    (7, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'manager', 'project manager', 5),
    (8, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'owner', 'project owner', 7),
    (9, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'admin', 'project admin', 8),
    (10, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'analyst', 'project or software analyst', 2),
    (11, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'release', 'release user', 2),
    (12, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'intern', 'senior graden', 1),
    (13, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'junior', 'junior grade', 12),
    (14, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'middle', 'middle grade', 13),
    (15, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'senior', 'senior grade', 14),
    (16, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'lead', 'project team leader', 15),
    (17, true, gen_random_uuid(), CURRENT_TIMESTAMP, 'safety', 'software safety', 2);