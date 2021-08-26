# Kafka for java Developers 

Sample code showcasing - Kafka producer , Kafka consumer. Unit / Integration testing of Kafka Components


#### Steps to start Kafka producer 
zookeeper-server-start.bat ..\..\config\zookeeper.properties
kafka-server-start.bat ..\..\config\server.properties



kafka-topics.bat --create --topic libEventTopic -zookeeper localhost:2181 --replication-factor 1 --partitions 1