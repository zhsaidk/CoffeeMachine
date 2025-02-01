--liquibase formatted sql

--changeset zhavokhir:3.0
ALTER TABLE coffee
ADD COLUMN count INT default 0;
--rollback drop column count