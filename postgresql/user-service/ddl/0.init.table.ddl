CREATE TABLE app.users
(
    uuid uuid NOT NULL,
    dt_create timestamp with time zone NOT NULL,
    dt_update timestamp with time zone NOT NULL,
    mail text NOT NULL,
    password text NOT NULL,
    fio text NOT NULL,
    role text NOT NULL,
    status text NOT NULL,
        constraint mail
            unique (mail),
);

ALTER TABLE IF EXISTS app.users
    OWNER to postgres;