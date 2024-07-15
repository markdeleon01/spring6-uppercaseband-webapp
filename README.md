# Spring6 MVC REST backend web application for UPPERCASE band

[![CircleCI](https://dl.circleci.com/status-badge/img/gh/markdeleon01/spring6-webmvc-mysql-uppercaseband-webapp/tree/main.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/markdeleon01/spring6-webmvc-mysql-uppercaseband-webapp/tree/main)

[![codecov](https://codecov.io/gh/markdeleon01/spring6-webmvc-mysql-uppercaseband-webapp/graph/badge.svg?token=WH38IFOEXS)](https://codecov.io/gh/markdeleon01/spring6-webmvc-mysql-uppercaseband-webapp)

This repository is for a RESTful web application built on Spring Framework 6 for my band's website.

This is a Spring Boot 3 web application that serves up articles about the UPPERCASE band.

The articles are categorized and displayed according to the various sections of the website,
such as highlights on the home page, events, discography section, and band information section;
they are stored in an H2 in-memory database and retrieved via JPA (relational database).

The article resources are exposed with the API route:
http://localhost:8080/api/v1/articles

JUnit5 tests were written to assert functionalities of the mapper, service, and controller classes.

## MySQL database
This web application supports the use of MySQL database by passing the Spring active profile ``localmysql``.
Otherwise, the web application runs against the H2 in-memory database by default.

## Project setup

1. Install Java SE 17 LTS:
   https://www.oracle.com/java/technologies/downloads/#java17
2. Install Apache Maven:
   https://maven.apache.org/download.cgi
3. Install MySQL Workbench:  https://dev.mysql.com/downloads/workbench/
4. Clone this project repository 
5. Open MySQL Workbench and execute the SQL statements found in the database initialization script:
   ``src\scripts\mysql-init.sql``
6. Navigate to the project folder and install
   ```sh
   mvn compile
   mvn test
   mvn package
   ```
7. Run the web application (and specify active profile to use local MySQL)
   ```sh
   java -jar -Dspring.profiles.active=localmysql target/uppercaseband-0.0.1-SNAPSHOT.jar
   ```
8. Open a browser window and navigate to: http://localhost:8080/api/v1/articles
9. Download the Postman desktop app:  https://www.postman.com