CREATE TABLE posts
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    text       VARCHAR(255) NULL,
    title      VARCHAR(255) NULL,
    user_id    BIGINT   DEFAULT NULL NULL,
    created_at datetime DEFAULT NULL NULL,
    updated_at datetime DEFAULT NULL NULL,
    CONSTRAINT PK_POSTS PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id   BIGINT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255) NULL,
    CONSTRAINT PK_ROLES PRIMARY KEY (id)
);

CREATE TABLE users
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    email              VARCHAR(255) NOT NULL,
    first_name         VARCHAR(255) NULL,
    last_name          VARCHAR(255) NULL,
    number_of_identity VARCHAR(255) NULL,
    password           VARCHAR(255) NULL,
    photo              VARCHAR(255) NULL,
    state              BIT          NOT NULL,
    phone              VARCHAR(255) NULL,
    created_at         datetime DEFAULT NULL NULL,
    updated_at         datetime DEFAULT NULL NULL,
    status             BIT      DEFAULT 0 NULL,
    CONSTRAINT PK_USERS PRIMARY KEY (id)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT PK_USERS_ROLES PRIMARY KEY (role_id, user_id)
);

CREATE INDEX FK_POSTS_ON_USER ON posts (user_id);

CREATE INDEX fk_userol_on_user ON users_roles (user_id);

ALTER TABLE posts
    ADD CONSTRAINT FK_POSTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id) ON UPDATE RESTRICT ON DELETE RESTRICT;

ALTER TABLE users_roles
    ADD CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id) ON UPDATE RESTRICT ON DELETE RESTRICT;