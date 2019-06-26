# spring-boot-proof

POCs with Spring Boot

## Docker Postgres 11 Container

Create container:

```bash 
docker run --rm  --name postgres-11-alpine \
       -p 5432:5432                        \
       -v xxx:/var/lib/postgresql/data     \
       postgres:11-alpine 
```

Create a database if not exists:

```
docker exec -it postgres-11-alpine  \
  psql -U postgres -tc  "SELECT 1 FROM pg_database WHERE datname =  'YYYY'"  \
| grep  1 ||  \
docker exec -it postgres-11-alpine  \
  psql -U postgres -tc  "CREATE DATABASE \"YYYY\" WITH OWNER = postgres ENCODING = 'UTF8' CONNECTION LIMIT = -1;"
```