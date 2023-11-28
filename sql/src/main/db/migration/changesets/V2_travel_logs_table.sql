set search_path to travel_logger;

CREATE SEQUENCE travel_logs_id start with 10000 increment by 1;

CREATE TABLE IF NOT EXISTS travel_logs
(
    id                          int default nextval('travel_logs_id'),
    vehicle_reg_number          text not null,
    owner_id                    int not null,
    created_date                timestamp without time zone NOT NULL default now(),
    updated_date                timestamp without time zone ,
    odometer_start              bigint not null,
    odometer_end                bigint not null,
    route                       text,
    description                 text,

    CONSTRAINT travel_logs_pkey PRIMARY KEY (id)
);
