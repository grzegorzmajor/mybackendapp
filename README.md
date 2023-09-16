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
| POST   | /dict                   | no          | needed         | add markup                            |
| DELETE | /dict/{id}              | no          | needed         | delete markup                         |
<br>

> #### Pagination
> Add to the url at the end according to the formula:<br>
> ?page=0&size=2

> #### Authentication
> You need to add the Authorization header containing the word "Bearer" and the token obtained after logging in with the /login endpoint.




## How to use this project...
###  ... in Docker Containers with your own settings.
#### First you need to download the repository from GitHub.<br>
If you are reading this, you must already have a link to it :P<br><br>
Review the application.yml and datasource.properties configuration files. You should not change anything in them, but it will allow you to understand the need to configure environment variables - more on that in a moment.<br><br>
#### Second you need to generate Self Signed Certificate - use the Keytool that comes with the JDK.<br>
When you need help with this, use the tutorial:<br>https://www.baeldung.com/spring-boot-https-self-signed-certificate#generating-keystore
<br>
Place the generated .p12 file in the resources/keystore directory (create the keystore directory if it doesn't exist), remember the data you provided during generation (password etc.)!
<br>
<br>
#### Third, configure the docker-compose.yml file.<br>
You can change environment variables for mariadb container. Duplicate them in the environment variables for the application container (mybackendapp). Compare the corresponding variables of both containers.
<br><br>
Change environments variables X_USER_NAME and HASHED_PASS.<br>
In X_USER_NAME just enter the username.
In HASHED_PASS, enter the password hashed with BCrypt.<br>
You can hash your password here:<br>
https://bcrypt-generator.com/
> ATTENTION!<br>
> There are three $ signs at the beginning of the generated hash! Generate a hash that has a number immediately after each of these characters!<br>
> I have no idea why this is happening, but if you do not meet this requirement, the application may not load the full hash. Then logging in with endpoint /login will be impossible.

Assign your own string to the JWT_SECRET variable - this is a variable that introduces additional security token generation during login.<br><br>
To variables KEY_STORE_ALIAS, KEY_STORE_PASS assign what you remembered when generating the certificate.<br>
To variable KEY_STORE_LOCATION assign the name of the .p12 file with your certificate - you need to keep "classpath:".<br>
After "classpath:" enter the directory you created and the name of the generated file.<br>If you have created a directory according to the instructions above and dropped the file into it - then just change the name of the file at the end to the name of your .p12 file.
#### Almost over!
Create a clean package with maven - in IntelliJ just use the menu in the Maven tab.<br><br>
Then find the lines in the docker-compose.yml file containing:
>#build .<br>
>image: mybackendapp-mybackendapp

and change them like this:
>build .<br>
>#image: mybackendapp-mybackendapp

Now you need to have Docker installed!<br>
In the terminal, in the folder where the docker-compose.yml file is, run the following command:
> docker-compose build

Now in the docker-compose.yml file, change the lines like this:
>#build .<br>
>image: mybackendapp-mybackendapp

and run in the terminal:
> docker-compose up

<br>
If you did everything correctly, the application is ready to run.<br><br>
In this way, you have launched the app locally on your computer, and you can shoot endpoints from the table above using the path https://localhost:1000<br>
unless you changed the port in the docker-compose.yml file ;P
<br><br> 
 If you want to use the application on your own server, I believe that you will be able to put it on the server yourself.
