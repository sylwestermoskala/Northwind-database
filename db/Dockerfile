FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD=admin
ENV MYSQL_DATABASE=northwind
COPY northwind-MySQL.sql /docker-entrypoint-initdb.d
