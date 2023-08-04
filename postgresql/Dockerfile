FROM postgres:15.3-alpine3.18

COPY user-service/ddl/00_init_database.sql /docker-entrypoint-initdb.d
COPY user-service/ddl/01_init_table.sql /docker-entrypoint-initdb.d
COPY user-service/ddl/10_insert_table_user.sql /docker-entrypoint-initdb.d
COPY audit-service/ddl/100_init_database.sql /docker-entrypoint-initdb.d
COPY audit-service/ddl/101_init_table.sql /docker-entrypoint-initdb.d



