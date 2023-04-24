-- liquibase formatted sql

-- changeset bbazarov:1

CREATE TABLE IF NOT EXISTS socks
(
    id       BIGSERIAL PRIMARY KEY,
    color    varchar,
    quantity int,
    cotton_Part int
);