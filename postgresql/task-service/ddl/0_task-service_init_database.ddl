create database tm_task;
create role "task" with
    LOGIN
    NOSUPERUSER
    NOCREATEDB
    NOCREATEROLE
    INHERIT
    NOREPLICATION
    CONNECTION LIMIT 5
    PASSWORD 'taskmanager';
