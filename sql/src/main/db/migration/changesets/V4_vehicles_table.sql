set search_path to travel_logger;

CREATE SEQUENCE vehicles_id start with 1 increment by 1;

CREATE TABLE IF NOT EXISTS vehicles
(
    id                          int default nextval('vehicles_id'),
    created_date                timestamp without time zone NOT NULL default now(),
    updated_date                timestamp without time zone,
    model                       text not null,
    manufacturer                text not null,
    reg_number                  text not null unique,
    owner_id                    int not null,

    CONSTRAINT vehicles_pkey PRIMARY KEY (id)
    );