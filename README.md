# Music App Spring Boot Client / Spotify Web Api

# Getting Started

## Spring Boot API connect MYSQl / KAFKA / SPOTIY With Feign Client

## Important! Use this Project studies only. Its necessary spotify account web developer

### Reference Documentation
This application is a simple sample how to connect Mysql/ Kafka and
Spotify Web Client use Spring boot.

### Requirement
Spring boot / Java 21 / Mysql Database / Kafka / Spotify Client Access Web API Account / Maven

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#web)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#using.devtools)
* [Apache Kafka](https://kafka.apache.org)
* [Spotify Web Api](https://developer.spotify.com/documentation/web-api)

### Guides
The following guides illustrate how to use some features concretely:
* [Leynner Roque - GIT Projects](https://github.com/LeynnerRoque)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Apache Kafka Client](https://kafka.apache.org)
* [Spotify Web Api Account](https://developer.spotify.com/documentation/web-api)


### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

### Create Environment variables 
Create Environment variables according application.properties

### Create Kafka Topics
bin/kafka-topics.sh --create --topic consulta-api-spotify   --bootstrap-server localhost:9092

bin/kafka-topics.sh --create --topic database-saved   --bootstrap-server localhost:9092
 
### Create Account Spotify Developers
Access Spotify page Web Developers documentations ->
[Spotify Account Developers](https://developer.spotify.com/documentation/web-api)

### How to use the API
1 - Create a Style Entity

2 - Create a Record Entity

3 - Create an Artists Entity

4 - Create an Album Entity

5 - Create Album from Spotify (with id album)

6 - Create Artists from Spotify (with id artists)