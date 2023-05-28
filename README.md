# My Backend App
## Introduction
### First
This project was launched as a backend for my website.
I wanted to build my portfolio.
Instead of creating Docker containers from ready-mades. I decided to write the backend for my site myself in Java, gaining experience in the topics I've been learning for the last few months. By the way, it will be a good part of my portfolio.
I wanted to understand the code I'm writing well, so I put off writing tests for later, so as not to interfere with understanding the dependencies between classes, packages, etc. I wanted to focus on Spring, JPA, entities and dependencies. In the first phase, I limited the tests to manual ones using Postman.

## Technologies used

### Environment
>IntelliJ IDEA Ultimate
### Language, framework and more
>#### Basic
>* Java 17, Maven,
>* Spring Boot 3.0,
>* Docker, Docker Compose,
>* Postman for manual testing 
>#### Database
>* MariaDB 10.5.8 in docker container 
>* Spring Data JPA
>#### For security
>* Spring Security, JWT,<br>
>* BCrypt,<br>
>* SSL with Self Signed Certificate created in Keytool
>#### Other dependencies 
>* Jakarta Validation 
### Design Patterns and more
* Builder, Facade, Ports & Adapters, <br> 
* SOLID

## Project architecture
### Domain Modules
#### Entities and his dependencies
<img src="readmeimages/entities.png" alt="entities schema">

#### Blog
<img src="readmeimages/blog.png" alt="blog module schema">

#### Login
<img src="readmeimages/login.png" alt="login module schema">

### Infrastructure
<img src="readmeimages/i1.png" alt="main functionality schema">
Security
<img src="readmeimages/i2.png"  alt="security schema">

## HTTP Methods

| METHOD | URL                     | PAGINATION  | AUTHENTICATION | DESCRIPTION                           | 
|--------|-------------------------|-------------|----------------|---------------------------------------|
| POST   | /login                  |             |                | login and returns a token             |
| GET    | /posts                  | yes, needed | no needed      | returning posts without unpublished   | 
| GET    | /posts/with-unpublished | yes, needed | needed         | returning posts with unpublished      |
| POST   | /posts                  | no          | needed         | adding new post                       |
| GET    | /paragraphs/{id}        | no          | needed         | returning paragraph with specified id |
| PATCH  | /paragraphs             | no          | needed         | update paragraph                      |
| GET    | /dict                   | no          | needed         | returning all markups                 |
| PATCH  | /dict                   | no          | needed         | add markup                            |
| PATCH  | /dict/{id}              | no          | needed         | delete markup                         |

## How to run the project in IntelliJ - every commit
>If you want to run the project in IntelliJ, you must first download it from GitHub to your environment.
>
>Secondly, you need to generate a Self-Signed Certificate yourself. It should be fully packed into a .P12 file - PKCS12 format.<br>
>Use the keytool that comes with the Java JDK<br>
>I refer to the tutorial: https://www.baeldung.com/spring-boot-https-self-signed-certificate

>Thirdly, in order to run the project, you must have the environment variables set:
>>* DB_HOST=localhost
>>* DB_NAME=
>>* DB_PASS=
>>* DB_ROOT_PASS=
>>* DB_USER=
>
>>* X_USER_NAME=
>>* HASHED_PASS=
>
>>* JWT_SECRET=
>
>>* KEY_STORE_ALIAS=
>>* KEY_STORE_PASS=
>>* KEY_STORE_LOCATION=
>
>Take a look at the application.yml and docker-compose.yml files - this will help you understand the meaning of environment variables.<br>
>These variables concern access to the MariaDB database, secret password for generating tokens by JWT, certificate data for SSL.
> 
>X_USER_NAME and HASHED_PASS are the data of the application user - these data will be used for logging in. This is a single user application, and it would be a waste of time to create a table with login data in the database.<br>
>So set these environment variables - your username, then use some tool to encrypt your password with the bCrypt algorithm and assign the generated string to the variable.<br>
>You can use for example: https://bcrypt-generator.com/
> 
>If you are only running the project in IntelliJ, you should set environment variables both for the main class file of the program and for the docker-compose.yml file:
>* Right click on MyBackendApplication and click "More Run/Debug" -> "Modify Run Configuration" then find "Environment variables" in blue "Modify options"
>* Right click on docker-compose.yml and click "Modify Run Configuration" then find "Environment variables" in blue "Modify options"
>
>Install Docker Desktop and connect IntelliJ to it if it doesn't do it automatically.
> 
>Now you can run the project.<br>
>First, run docker-compose.yml (Right click -> Run) and wait for it to start the database container.<br>
>Then run MyBackendApplication (Right click -> Run) and wait for the application to start up.<br>
> 
>You can shoot endpoints using Postman, if you set everything up correctly, the application will respond without logging in to the:
>* GET request at https://localhost:443/posts?page=0&size=2 with status 200
>* POST request at https://localhost:443/login with body { "name": "your_user_name", "password": "your_password" } with status 200 and returning a token.<br>
>
>All others in the table above after logging in and setting the token (Bearer Token authorization in Postman) 
>
## How to install an application in docker containers
>Download this project from GitHub to your environment.<br>
> 
>Select the Branch marked with the version you want and load the commit related to it.<br>
>There is one right now, but there will be more.<br>
> 
>Use the section above, generate a self-signed certificate and encode the password<br>
>Read about environment variables in the section above.<br>
> 
>Since I use Windows, I'll explain using Windows PowerShell.<br>
>I assume you already know everything about environment variables.
>
>Now set environment variables for Maven Runner to.<br>
>Click: Maven tab -> wrench icon -> Maven Settings.<br>
>Then open: Build, Execution, Deployment -> Build Tools -> Maven -> Runner<br>
>and set environment variables there.
>
>