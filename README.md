# Cooking Hero - recipes microservice

Recipes microservice for Cooking Hero App.

## Tecs 

* Main Language: Java 8+
* Main Framework: [Spring Boot](http://projects.spring.io/spring-boot/)
* Libraries: maven, swagger, lombok

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven](https://maven.apache.org)
- [Neo4j](https://neo4j.com/download/)

### Installing

- Install prerequisites
- Initialize and start a neo4j db

```shell
sudo neo4j start
```

- Got to cooking-hero-recipes main folder and start the service with spring boot plugin: 

```shell
mvn spring-boot:run
```

## API access

You can access to the swagger-api in this url:

http://localhost:8080/api

## Authors

- [OjoDev](https://github.com/ojoDev)
