-- liquibase formatted sql

-- changeset oss:1
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email varchar(50),
    username varchar(16) NOT NULL,
    password varchar(16) NOT NULL,
    first_name varchar(16) NOT NULL,
    last_name varchar(16) NOT NULL,
    phone varchar(12) NOT NULL,
    image varchar(255)
);

-- changeset oss:2
CREATE TABLE user_role (
    user_id BIGINT NOT NULL REFERENCES users(id),
    role varchar(20) NOT NULL
);

-- changeset oss:3
CREATE TABLE ads(
    pk BIGSERIAL PRIMARY KEY,
    title varchar (255) ,
    price INT ,
    description TEXT,
    user_id BIGSERIAL REFERENCES users(id),
    image varchar(255)
);

-- changeset oss:4
CREATE TABLE comments (
    pk BIGSERIAL PRIMARY KEY,
    ad_id BIGINT REFERENCES ads(pk),
    user_id BIGINT REFERENCES users(id),
    create_at TIMESTAMP,
    text TEXT
);


