# Telephone Contact Agenda

##Version: 1


### About
This microservice is an POC for a telephone contact agenda.
It uses template rendering for frontend.
I would have use Angular 8 for the frontend if it was allowed.

This POC microservice has **no tests and no JWT security for endpoints**.
It has a **free text search** functionality for contacts in their email, name and number.


## Technologies used
1) Java 11
2) H2 embedded database
3) Spring Boot
4) Hibernate
5) Thymeleaf for frontend template rendering(should have used **Angular**)
6) FlywayDB
7) Mapstruct
8) Bootstrap

## How to run

Go to the root folder of the project and simply run:
``docker-compose up``

This will build the docker image and run the application inside the container.

It will run by default on ``localhost:8080``

## Application screenshots

###Main page with contacts
![Main_page]https://github.com/sorinvisan89/telephone-app/blob/master/images/Screenshot%202021-04-16%20at%2020.42.50.png

###Validation example for inputs
![Validation_input](https://github.com/sorinvisan89/telephone-app/blob/master/images/Screenshot%202021-04-16%20at%2020.41.46.png)

###Error page when email already exists
![Error_page](https://github.com/sorinvisan89/telephone-app/blob/master/images/Screenshot%202021-04-16%20at%2020.42.00.png)

###Free text search in contacts email, number and name
![Free_text_search](https://github.com/sorinvisan89/telephone-app/blob/master/images/Screenshot%202021-04-16%20at%2020.43.04.png)


    