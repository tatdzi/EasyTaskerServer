\c tm_report
create schema if not exists app;
alter schema app owner to root;
grant usage on schema app to "report";

create table if not exists app.reports
(
    uuid         uuid         not null,
    dt_create    timestamp(3) not null,
    dt_update    timestamp(3) not null,
    status       text                 ,
    type         text                 ,
    discription  text                 ,
    param        text                 ,
    constraint project_pkey
        primary key (uuid)
);
alter table app.project owner to root;
grant insert, select, update on app.project to "report";

