# Comand Query Repository Segregation  

## Overview 
CQRS stands for Command Query Responsibility Segregation. It's a pattern that was first coin by Greg Young. 



## Demo Overview 


###  Demo Artifacts 
1. Axon Server Container 
2. MongoDB Container 
3. User-Management-OAuth2.0-Service 
4. User-Management-Command Micro-service
5. User-Management-Query Micro-service  
6. User-Management-ApiGateway

### Installation Steps 
1. Create docker network
   ```shell
    docker network create --attachable -d bridge cqrs-demo-network
    ```
	Verify Axon server by openning http://192.168.99.100:8024/
	
2. Create Axon Server docker container
    
    ```shell
    docker run -d --name axon-server -p 8024:8024 -p 8124:8124 --network cqrs-demo-network --restart always axoniq/axonserver:latest
    ```
3. Create MongoDB docker container

    ```shell
    docker run -it -d --name mongo-container -p 27017:27017 --network cqrs-demo-network --restart always mongo
    ```
4.  Create User-Management-OAuth2.0-Service image 
	```shell
	docker build -t users-oauth-2.0 .
	```

5. Create User-Management-OAuth2.0-Service image 
   ```shell
   docker build -t users-oauth-2.0 .
   ```
5. Create User-Management-Command-Service image 
   ```shell
   docker build -t users-cmd .
   ```
   
6. Create User-Management-Query-Service image 
   ```shell
   docker build -t users-query .   
  
8. Crete User-Management-OAuth2.0-Service container 
   ```shell
   docker run -p 8084:8084 --name users-oauth-2.0 --network cqrs-demo-network -e SPRING_PROFILES_ACTIVE=docker -d users-oauth-2.0  
   
8. Create User-Management-Command-Service container
   ```shell
   docker run -p 8081:8081 --name users-cmd --network cqrs-demo-network -e SPRING_PROFILES_ACTIVE=docker -d users-cmd
   
9. Create User-Management-Query-Service container
   ```shell
   docker run -p 8082:8082 --name users-query --network cqrs-demo-network -e SPRING_PROFILES_ACTIVE=docker -d users-query  
   
10. View docker images and their details 
	```shell
	docker system df -v