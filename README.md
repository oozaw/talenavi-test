# Spring Movie CRUD
This project is a simple CRUD application using Spring Boot, Spring Data JPA, and MySQL.

## Prerequisites
- Java 11
- Maven
- MySQL

## Setup Instructions
1. Clone this repository.
```bash
git clone https://github.com/oozaw/talenavi-test.git
cd talenavi-test
```

2. Run MySQL Query within database.sql file to create database and table.
3. Update the database connection properties in `src/main/resources/application.properties`.
4. Build the project.
```bash
mvn clean install
```
5. Run the project.
```bash
mvn spring-boot:run
```
6. The application will be running at `http://localhost:8080`.
7. You can import the Postman collection to test the API.
