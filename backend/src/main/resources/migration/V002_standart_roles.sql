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
    prev_role       BIGSERIAL,
    project_id      BIGSERIAL NOT NULL,
    CONSTRAINT fk_project_project_id FOREIGN KEY (role_id) REFERENCES project.project(id) ON DELETE CASCADE,
    CONSTRAINT uq_project_role UNIQUE (code, project_id)
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
    CONSTRAINT uq_project_user_roles UNIQUE (user_id, role_id)
);