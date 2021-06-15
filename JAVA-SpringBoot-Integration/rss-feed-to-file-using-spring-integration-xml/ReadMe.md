#Spring Integration Demo (using xml configuration)

## Use case 
Reading data from website RRS feed http://feeds.bbci.co.uk/news/world/rss.xml and write to a local file, using spring integration framework leveraging XML file configuration.

## Implementation Details 
Create a new file /integration/integration.xml under resource folder, ref the file in SpringbootApplication main class using @ImportResource annotation. define inbound-channel-adapter, outbound-channel-adapter and transformer configuration.  



## Build Info

To build this poject use maven command 
```shell
mvn clean install
```
To run this project use maven command 
```shell
mvn clean spring-boot:run
```


#### Reference 
Spring integration official page: https://spring.io/guides/gs/integration/