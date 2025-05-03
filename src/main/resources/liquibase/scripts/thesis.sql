-- liquibase formatted sql

-- changeset oss:1
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email varchar(50) UNIQUE NOT NULL,
    username varchar(128) NOT NULL,
    password varchar(128) NOT NULL,
    first_name varchar(16) NOT NULL,
    last_name varchar(16) NOT NULL,
    phone varchar(12) NOT NULL,
    role varchar(32) NOT NULL,
    image varchar(255)
);

-- changeset oss:2
CREATE TABLE ads(
    pk BIGSERIAL PRIMARY KEY,
    title varchar (255) ,
    price INT NOT NULL,
    description TEXT NOT NULL,
    user_id BIGINT REFERENCES users(id),
    image varchar(255)
);

-- changeset oss:3
CREATE TABLE comments (
    pk BIGSERIAL PRIMARY KEY,
    ad_id BIGINT REFERENCES ads(pk),
    user_id BIGINT REFERENCES users(id),
    create_at TIMESTAMP,
    text TEXT
);










