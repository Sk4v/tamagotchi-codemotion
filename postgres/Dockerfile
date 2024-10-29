FROM postgres:latest

ENV POSTGRES_DB=mydatabase
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=mypassword

COPY schema.sql /docker-entrypoint-initdb.d/
COPY etf.csv /docker-entrypoint-initdb.d/
