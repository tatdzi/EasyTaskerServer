FROM postgres:15.3-alpine3.18

COPY user-service/ddl/0_user-service_init_database.sql /docker-entrypoint-initdb.d
COPY user-service/ddl/1_user-service_init_table.sql /docker-entrypoint-initdb.d

COPY user-service/ddl/2_user-service_insert_table_user.sql /docker-entrypoint-initdb.d
COPY audit-service/ddl/0_audit-service_init_database.sql /docker-entrypoint-initdb.d
COPY audit-service/ddl/1_audit-service_init_table.sql /docker-entrypoint-initdb.d

COPY task-service/ddl/0_task-service_init_database.sql /docker-entrypoint-initdb.d
COPY task-service/ddl/1_task-service_init_table.sql /docker-entrypoint-initdb.d
COPY task-service/ddl/2_task-service_insert_table_project.sql /docker-entrypoint-initdb.d
COPY task-service/ddl/3_task-service_insert_table_project-staff.sql /docker-entrypoint-initdb.d
COPY task-service/ddl/4_task-service_insert_table_task.sql /docker-entrypoint-initdb.d

COPY report-service/ddl/0_report-service_init_database.sql /docker-entrypoint-initdb.d
COPY report-service/ddl/1_report-service_init_table.sql /docker-entrypoint-initdb.d



