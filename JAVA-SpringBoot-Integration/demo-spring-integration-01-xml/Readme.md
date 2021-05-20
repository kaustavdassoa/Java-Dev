# Spring Integration - with xml configuration 

This demo application has three modules 

**Common** - Common domain module - message definations.

**Producer** - Message producer module.

**Consumer** - Message consumer modules. 




### Docker command to start rabbitMQ 

```shell script
docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq-1 rabbitmq:3-management
```