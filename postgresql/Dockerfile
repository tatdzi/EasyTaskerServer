FROM postgres:15.3-alpine3.18

COPY user-service/ddl/0_user-service_init_database.ddl /docker-entrypoint-initdb.d
COPY user-service/ddl/1_user-service_init_table.ddl /docker-entrypoint-initdb.d
COPY user-service/ddl/2_user-service_insert_table_user.sql /docker-entrypoint-initdb.d
COPY audit-service/ddl/0_audit-service_init_database.ddl /docker-entrypoint-initdb.d
COPY audit-service/ddl/1_audit-service_init_table.ddl /docker-entrypoint-initdb.d



