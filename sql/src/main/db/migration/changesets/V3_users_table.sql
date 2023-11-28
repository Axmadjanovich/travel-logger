set search_path to travel_logger;

CREATE SEQUENCE users_id start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS users
(
    id                          int default nextval('users_id'),
    created_date                timestamp without time zone NOT NULL default now(),
    updated_date                timestamp without time zone,
    full_name                   text not null,
    passport_serial_number      text not null,
    birth_date                  timestamp without time zone,

    CONSTRAINT user_pkey PRIMARY KEY (id)
);
