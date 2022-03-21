# Online TODO List
Deloitte Todo web application

## Overview
The purpose of this assignment is to create a Todo application with functionality as stated below,
*User can sign in using unique login and password securely 
*User can view her/his task list
*User can add/remove task
*All changes can be persistent to allow view them in next sign in by the same user
*Each task should display the date of last updates and description
*User can check/uncheck any task on their list

## Technology Stack Used
* Java 1.8 with Spring Boot, Spring Security, JPA
* Maven as a dependency management tool
* Log4j for logging
* Junit for integration testing
* JSP, Bootstrap, HTML5 and CSS3 for frontend


How to run
```
$ git clone 
$ cd OnlineTodo
$ mvn clean install
```

if you are running from eclipse, follow this steps.
Right click on MainApplication and run as Java Application then paste below url in browser or click on it to view it in browser.
http://localhost:8080/


## Deployable war
Application war is commited inside War directory and after deploying to tomcat, it can be run at http://localhost:8080/DeloitteTodoApp
User can login by below credentials stored in DB.
* 1)username: Testuser, password: pwd123

H2 console: http://localhost:8080/h2 if deployed on tomcat, then it will be http://localhost:8080/DeloitteTodoApp/h2