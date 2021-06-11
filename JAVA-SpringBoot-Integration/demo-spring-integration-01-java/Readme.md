# Spring Integration - with java annotation 

This demo application has three modules - Common Module that holds the common data model, Producer Module which host rest controller 
to send message and Consumer module that host service that reads messages from rabbitMQ and stores it in H2 database. The application uses 
XML base configurationto define channels , and its intermediary.

### Steps to test application modules

#### start rabbitMQ on a docker container
Use the following command to install rabbitMQ on the docker container, Once the docker container started successfully use the below link to access the rabbitMQ home page 
http://192.168.1.22:15672/ .Use default credentials - guest/guest to login.

```shell script
docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq-1 rabbitmq:3-management
```

#### start Producer module 
Start producer microservice, once producer module starts successfully sent a post message to http://localhost:8080/messagesender/v01/sendmessage.

#### start Consumer module 
Start consumer microservice, once consumer module starts successfully login to H2-Console http://localhost:8081/h2-console (use jdbc url as jdbc:h2:mem:messagedb). One should be able to view the posted message on the database table MESSAGES.


