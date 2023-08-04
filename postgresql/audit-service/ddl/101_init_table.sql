\c tm_audit
create schema if not exists app;
alter schema app owner to root;
grant usage on schema app to "audit";

create table if not exists app.audit
(
    uuid      uuid         not null,
    dt_create timestamp(3) not null,
    user_uuid uuid         not null,
    user_mail text         not null,
    user_fio  text         not null,
    user_role text         not null,
    text      text         not null,
    type      text         not null,
    id        text         not null,
    constraint users_pkey
    primary key (uuid)
 );

alter table app.audit owner to root;
grant insert, select, update on app.audit to "audit";