create database tm_user;
create role "user" with
    LOGIN
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT 5
    PASSWORD 'uroot';
