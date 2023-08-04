create database tm_audit;
create role "audit" with
    LOGIN
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT 5
    PASSWORD 'a7530305';
