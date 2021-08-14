FROM postgres:13.2
COPY ./init.sql /docker-entrypoint-initdb.d/1-init.sql
CMD ["docker-entrypoint.sh", "postgres"]