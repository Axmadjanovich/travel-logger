CREATE SCHEMA IF NOT EXISTS travel_logger;
grant all privileges on schema travel_logger to travel_logger_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA travel_logger GRANT ALL PRIVILEGES ON TABLES TO travel_logger_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA travel_logger GRANT ALL PRIVILEGES ON FUNCTIONS TO travel_logger_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA travel_logger GRANT ALL PRIVILEGES ON SEQUENCES TO travel_logger_user;
