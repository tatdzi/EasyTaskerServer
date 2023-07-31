create schema if not exists app;
alter schema app owner to postgres;
grant usage on schema app to "user";

create table if not exists app.users
(
    uuid      uuid         not null,
    dt_create timestamp(3) not null,
    dt_update timestamp(3) not null,
    mail      text         not null,
    password  text         not null,
    fio       text         not null,
    role      text         not null,
    status    text         not null,
    constraint users_pkey
    primary key (uuid),
    constraint mail
    unique (mail)
    );
alter table app.users owner to postgres;
grant insert, select, update on app.users to "user";