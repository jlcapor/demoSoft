ALTER TABLE roles
    ADD user_id BIGINT NULL;

ALTER TABLE roles
    ADD CONSTRAINT FK_ROLES_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE users_roles
DROP
FOREIGN KEY fk_userol_on_role;

ALTER TABLE users_roles
DROP
FOREIGN KEY fk_userol_on_user;

DROP TABLE users_roles;