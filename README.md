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

| METHOD  | URL                     | PAGINATION | AUTHENTICATION | DESCRIPTION                           | 
|---------|-------------------------|------------|----------------|---------------------------------------|
| GET     | /posts                  | yes        | no needed      | returning posts without unpublished   | 
| GET     | /posts/with-unpublished | yes        | needed         | returning posts with unpublished      |
| POST    | /posts                  | n/d        | needed         | adding new post                       |
| GET     | /paragraphs/{id}        | n/d        | needed         | returning paragraph with specified id |
| PATCH   | /paragraphs             | n/d        | needed         | update paragraph                      |
| GET     | /dict                   | n/d        | needed         | returning all markups                 |
| PATCH   | /dict                   | n/d        | needed         | add markup                            |
| PATCH   | /dict/{id}              | n/d        | needed         | delete markup                         |

## How to use the project

>If you want to use this project, you must first download it from GitHub to your environment. I use IntelliJ IDEA Ultimate. :) 
> 
>Select Branch marked with the version you are interested in. There is currently only one version! :) But there will be more. I will add changes to this file.
> 
>Secondly, you need to generate a Self Signed Certificate yourself. It should be fully packed into a .P12 file.<br>
>Use the keytool that comes with the Java JDK<br>
>I refer to the tutorial: https://www.baeldung.com/spring-boot-https-self-signed-certificate
>
>Thirdly, in order to run the project, you must have the environment variables set:
>* DB_HOST=
>* DB_NAME=
>* DB_PASS=
>* DB_USER=
>
>* X_USER_NAME=
>* HASHED_PASS=
>
>* JWT_SECRET=
> 
>* KEY_STORE_ALIAS=
>* KEY_STORE_PASS=
>* KEY_STORE_LOCATION=
>
>Take a look at the application.yml file - this will help you understand the meaning of environment variables.<br>
>These variables concern access to the MariaDB database, secret password for generating tokens by JWT, certificate data for SSL.
> 
>X_USER_NAME and HASHED_PASS are the data of the application user - these data will be used for logging in. This is a single user application, and it would be a waste of time to create a table with login data in the database.<br>
>So set these environment variables - your username, then use some tool to encrypt your password with the bCrypt algorithm  and assign the generated string to the variable.<br>
>You can use for example: https://bcrypt-generator.com/
> 