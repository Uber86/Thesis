-- liquibase formatted sql

-- changeset oss:1
CREATE TABLE users (
    id BIGSERIAL,
    username varchar(16) NOT NULL,
    password varchar(16) NOT NULL,
    first_name varchar(16) NOT NULL,
    last_name varchar(16) NOT NULL,
    phone varchar(12) NOT NULL,
);

-- changeset oss:2
CREATE TABLE user_role (
    user_id BIGSERIAL NOT NULL REFERENCES users(id),
    role varchar(20) NOT NULL
);