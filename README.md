# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/maven-plugin/reference/html/#build-image)
* [JDBC API](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-sql)

## Run locally

1. [Install Docker](https://docs.docker.com/get-docker/)
2. Execute
    * `docker run -d -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=cars -p 5432:5432 postgres`
3. Execute
   * `mvn clean package`
   * `java -jar target/spring-jdbc-template-0.0.1-SNAPSHOT.jar`

## Run on Docker-compose

1. [Install Docker](https://docs.docker.com/get-docker/)
2. [Install Docker-Compose](https://docs.docker.com/compose/install/)
3. Execute:
   * `mvn spring-boot:build-image `
   * `docker-compose up`

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Relational Data using JDBC with Spring](https://spring.io/guides/gs/relational-data-access/)
* [Managing Transactions](https://spring.io/guides/gs/managing-transactions/)


