\c tm_task
create schema if not exists app;
alter schema app owner to root;
grant usage on schema app to "task";

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
alter table app.project owner to root;
grant insert, select, update on app.project to "task";

create table if not exists app.task
(
    uuid         uuid         not null,
    dt_create    timestamp(3) not null,
    dt_update    timestamp(3) not null,
    project      uuid         not null,
    title        text         not null,
    discription  text                 ,
    status       text                 ,
    implementer  uuid                 ,
    constraint task_pkey
    primary key (uuid)
    );
alter table app.task owner to root;
grant insert, select, update on app.task to "task";

create table if not exists app.project_staff
(
    project_uuid   uuid         not null,
    staff_uuid     uuid         not null,
    constraint parent
        foreign key (project_uuid) references app.project
);

alter table app.project_staff owner to root;
grant insert, select, update on app.project_staff to "task";
