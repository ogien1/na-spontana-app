# Na Spontana

[![Build Status](https://travis-ci.org/ogien1/na-spontana-app.svg?branch=master)](https://travis-ci.org/ogien1/na-spontana-app)


## Build war:
`mvn -Dmaven.test.skip=true install`

## Run in embedded tomcat:
`mvn spring-boot:run`

## Run tests (requires postgres):
```bash
docker run -p 5432:5432 -e POSTGRES_PASSWORD=spontanp_test -e POSTGRES_USER=spontanu_test -e POSTGRES_DB=spontandb_test -d postgres
mvn test
```
