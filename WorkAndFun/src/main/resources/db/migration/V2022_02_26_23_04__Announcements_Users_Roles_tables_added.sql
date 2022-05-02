CREATE TABLE announcement
(
    a_id                     BIGINT AUTO_INCREMENT NOT NULL,
    a_type                   VARCHAR(255)          NOT NULL,
    a_header                 VARCHAR(255)          NOT NULL,
    a_service_type           VARCHAR(255)          NOT NULL,
    a_city                   VARCHAR(255)          NOT NULL,
    a_city_district          VARCHAR(255)          NULL,
    a_unit                   VARCHAR(255)          NULL,
    a_price                  VARCHAR(255)          NULL,
    owner_user_id            BIGINT                NULL,
    a_voivodeship            VARCHAR(255)          NOT NULL,
    a_date                   datetime              NULL,
    a_name_of_advertiser     VARCHAR(255)          NULL,
    a_email                  VARCHAR(255)          NOT NULL,
    a_is_price_negotiable    BIT(1)                NOT NULL,
    a_description            VARCHAR(255)          NULL,
    a_phone_number           VARCHAR(255)          NULL,
    a_price_addition_comment VARCHAR(255)          NULL,
    CONSTRAINT pk_announcement PRIMARY KEY (a_id)
);

CREATE TABLE roles
(
    r_id   BIGINT AUTO_INCREMENT NOT NULL,
    r_name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_roles PRIMARY KEY (r_id)
);

CREATE TABLE user
(
    u_id            BIGINT AUTO_INCREMENT NOT NULL,
    u_username      VARCHAR(255)          NOT NULL,
    u_password      VARCHAR(255)          NOT NULL,
    u_first_name    VARCHAR(255)          NOT NULL,
    u_last_name     VARCHAR(255)          NOT NULL,
    u_phone_number  VARCHAR(255)          NULL,
    u_email         VARCHAR(255)          NOT NULL,
    u_age           INT                   NULL,
    u_voivodeship   INT                   NOT NULL,
    u_city          VARCHAR(255)          NOT NULL,
    u_city_district VARCHAR(255)          NULL,
    CONSTRAINT pk_user PRIMARY KEY (u_id)
);

CREATE TABLE users_roles
(
    role_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL
);

ALTER TABLE announcement
    ADD CONSTRAINT FK_ANNOUNCEMENT_ON_OWNER_USER FOREIGN KEY (owner_user_id) REFERENCES user (u_id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_users_roles_on_user FOREIGN KEY (user_id) REFERENCES user (u_id);

ALTER TABLE users_roles
    ADD CONSTRAINT fk_users_roles_on_role FOREIGN KEY (role_id) REFERENCES roles (r_id);