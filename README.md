# SpringBoot Web App

A basic Education Management System built using Spring Boot, Spring Data JPA, Thymeleaf, MySQL, HTML and CSS.

## Technologies Used

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* Thymeleaf
* MySQL
* HTML
* CSS
* Maven

## Features

* User Registration
* User Login
* User Profile
* Edit Profile
* Admin Dashboard
* View Users
* Add Users
* Edit Users
* Delete Users
* Role-based functionality

## Database Setup

Create a MySQL database:

```sql
CREATE DATABASE Spring_Web_App5;
```

Then configure your MySQL username and password in:

`src/main/resources/application.properties`

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/Spring_Web_App5
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## How to Run

1. Clone the repository.
2. Open the project in Eclipse or another Java IDE.
3. Make sure Java 17 and MySQL are installed.
4. Create the `Spring_Web_App5` database.
5. Configure your MySQL credentials.
6. Run:

```text
SpringBootWebApp44Application.java
```

7. Open:

```text
http://localhost:8080
```

## Project Structure

```text
src/
 └── main/
     ├── java/
     │   └── Controllers, Services, Repository, Entity
     │
     └── resources/
         ├── static/
         │   ├── CSS
         │   ├── images
         │   └── index.html
         │
         ├── templates/
         │   ├── login.html
         │   ├── register.html
         │   ├── Profile.html
         │   └── adminDashboard.html
         │
         └── application.properties

pom.xml
README.md
```

## Author

Rahul
