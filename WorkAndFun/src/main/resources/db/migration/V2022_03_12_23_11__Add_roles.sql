ALTER TABLE users_roles
    ADD PRIMARY KEY (role_id, user_id);

ALTER TABLE roles
    ADD CONSTRAINT uc_roles_r_name UNIQUE (r_name);

ALTER TABLE user
    ADD CONSTRAINT uc_user_u_email UNIQUE (u_email);

ALTER TABLE user
    ADD CONSTRAINT uc_user_u_username UNIQUE (u_username);

insert into roles (r_id, r_name)
values (1, 'ADMIN'),
       (2, 'APPLICATION_USER');