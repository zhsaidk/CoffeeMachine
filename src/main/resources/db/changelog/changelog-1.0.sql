--liquibase formatted sql

--changeset zhavokhir:1.0
CREATE TABLE coffee(
    id SERIAL PRIMARY KEY ,
    name VARCHAR(128)
);
--rollback drop table coffee

--changeset zhavokhir:1.1
CREATE TABLE ingredient(
    id SERIAL PRIMARY KEY ,
    title VARCHAR(128) UNIQUE NOT NULL ,
    total INT default 1000 NOT NULL
);
--rollback drop table ingredient

--changeset zhavokhir:1.2
CREATE TABLE recipe(
    id SERIAL PRIMARY KEY ,
    coffee_id INT references coffee(id) on DELETE CASCADE ,
    mass INT NOT NULL ,
    ingredient_id INT references ingredient(id) on DELETE RESTRICT
);
--rollback drop table recipe