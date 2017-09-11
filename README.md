# LEARNING CALENDAR APPLICATION BACK-END
> A university learning calendar management application back-end

## Table of contents
*[Description](##Description)
*[Deployment](##Deployment)
*[Note](##Note)

## Description
This repository contains back-end of the web application, the front-tend is available at [learningCalendar](http://example.com).  

In this back-end project, I use Java Spring Boot, Hibernate, Spring Security, Spring Websocket, Spring Schedule to program services serve the processes of application.
 
The most special function of this application is automatically generating learing calendar for university/college using Genetic Algorithm.
You can find the fully explanation of the way I apply Genetic Algorithm at [here](https://google.com )

## Deployment
1. Create a database 'danavtc' in MySQL server.
2. Import the /database/lastedDB.sql into the database 'danavtc' just created.
3. Edit the file /src/main/resources/local.properties, change the datasource url, name, password to your environment value.
4. Open command line under project folder and type `maven package`.
5. Deploy the war file /target/learning_calendar_backend-0.0.1-SNAPSHOT.war on Tomcat Server version 8 or later, set HTTP port: 8080.

**If you want to edit, research or reuse the code, you can open project by IntelliJ IDEA version 2016.3 or above. Configure Tomcat server, 

Now you can look over the application's APIs through Swagger UI at http://localhost:8080/swagger-ui.html

## Note
This is my graduate project at university and according to the real requirements of Danang Vocational Training College,
I have to make the database by Vietnamese and the code is mixed by Vietnamese and English as well.
I'm so sorry if that give you confusion, specially for those who don't know Vietnamese.

If you have any question, email me at nmtri881994@gmail.com.

[⬆ back to top](#Table of contents)