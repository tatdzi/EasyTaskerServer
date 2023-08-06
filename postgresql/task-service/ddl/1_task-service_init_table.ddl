\c tm_task
create schema if not exists app;
alter schema app owner to postgres;
grant usage on schema app to "task";

create table if not exists app.user
(
    uuid      uuid         not null,
    constraint user_pkey
        primary key (uuid)
);

alter table app.user owner to postgres;
grant insert, select, update on app.user to "task";

create table if not exists app.project
(
    uuid         uuid         not null,
    dt_create    timestamp(3) not null,
    dt_update    timestamp(3) not null,
    name         text                 ,
    discription  text                 ,
    manager      uuid                 ,
    status       text                 ,
    constraint project_pkey
        primary key (uuid)
);
alter table app.project owner to postgres;
grant insert, select, update on app.project to "task";

create table if not exists app.task
(
    uuid         uuid         not null,
    dt_create    timestamp(3) not null,
    dt_update    timestamp(3) not null,
    project      text         not null,
    title        text         not null,
    discription  text                 ,
    status       text                 ,
    implementer  uuid                 ,
    constraint task_pkey
    primary key (uuid)
    );
alter table app.task owner to postgres;
grant insert, select, update on app.task to "task";

create table if not exists app.project_staff
(
    project_uuid   uuid         not null,
    user_uuid      uuid         not null,
    constraint parent
        foreign key (project_uuid) references app.project,
    constraint child1
        foreign key (user_uuid) references app.user

);
alter table app.project_staff owner to postgres;
grant insert, select, update on app.project_staff to "task";
