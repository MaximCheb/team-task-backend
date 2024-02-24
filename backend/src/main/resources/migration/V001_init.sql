CREATE SCHEMA IF NOT EXISTS users;

--reference is public.t_currency in mdm database
CREATE TABLE IF NOT EXISTS users.user_profile
(
    id              BIGSERIAL PRIMARY KEY,
    active          BOOLEAN     NOT NULL DEFAULT true,
    uuid            UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(), --uuid
    create_date     TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
    username        VARCHAR(64) UNIQUE NOT NULL,
    password        VARCHAR,
    firstname       VARCHAR(128),
    secondname      VARCHAR(128),
    email           VARCHAR(64) UNIQUE,
    phone           VARCHAR(64) UNIQUE
);

CREATE TABLE IF NOT EXISTS users.system_role
(
    id              BIGSERIAL PRIMARY KEY,
    active          BOOLEAN     NOT NULL DEFAULT true,
    uuid            UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(), --uuid
    create_date     TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
    code            VARCHAR(64) UNIQUE NOT NULL,
    name            VARCHAR(128) UNIQUE NOT NULL,
    prev_role       BIGSERIAL
    CONSTRAINT fk_prev_role FOREIGN KEY (prev_role) REFERENCES users.system_role(id) ON DELETE SET NULL
);


CREATE SCHEMA IF NOT EXISTS project;

--reference is public.t_currency in mdm database
CREATE TABLE IF NOT EXISTS project.project
(
    id              BIGSERIAL PRIMARY KEY,
    active          BOOLEAN   NOT NULL DEFAULT true,
    uuid            UUID UNIQUE NOT NULL DEFAULT gen_random_uuid(), --uuid
    create_date     TIMESTAMP NOT NULL  DEFAULT CURRENT_TIMESTAMP,
    name            VARCHAR(128) NOT NULL,
    initials        VARCHAR(16) UNIQUE NOT NULL,
    description     TEXT NOT NULL
);

CREATE INDEX idx_project_name ON project.project USING BTREE (name);

