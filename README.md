# LEARNING CALENDAR APPLICATION
# learningCalendarBackend
## Description
This repository contains back-end of the web application, the front-tend is available at[learningCalendar](http://example.com).  

In this back-end project, I use Java Spring Boot, Hibernate, Spring Security, Spring Websocket, Spring Schedule to write services serve the processes of application.
 
The most special function of this application is automatically generating learing calendar for university/college using Genetic Algorithm.  
You can find the fully explanation of the way I apply Genetic Algorithm at[here](ttps://google.com )

## Deployment
1. Create a database 'danavtc' in MySQL server.
2. Import the ./database/lastedDB.sql into the database 'danavtc' just created.
3. Open command line under project folder and type `maven package`.
4. Deploy the war file ./target/learning_calendar_backend-0.0.1-SNAPSHOT.war on Tomcat Server version 8 or later, set HTTP port: 8080.
 
Now you can look over the application's APIS through Swagger UI at http://localhost:8080/swagger-ui.html

## Note
This is my graduate project at university and according to the real requirements of Danang Vocational Training College,
I have to make the database by Vietnamese and the code is mixed by Vietnamese and English as well.
I'm so sorry if that give you confusion, specially to who does'nt know Vietnamese.
