## Goals
    - Mastering Spring Webflux
    - Reactive programming and Kafka
    - ArchUnit as code and architecture best practices regulator
    
## Spring cloud stream ?
- Is a system binder abstraction. It allows connecting to different messaging systems like kafka/rabbit-mq without changing
application code.
- It's a declarative programming model: uses annotation and functional programming; declared methods will run automatically
when receiving data.
- Works with spring boot and spring webflux 
- The name of function declared as bean represents a topics :
      Ex: for topic named "toLowerCase-out-0"
    
      <i>public Function<String, String> toLowerCase() {
      return receivedValue -> receivedValue.toLowerCase();
      }</i>

## KAFKA with docker on Windows 
        - Open 2 kafka terminals of created container 'my-kafka-broker-container', kafka-docker-compose.yml.
            Commandline : docker exec -it kafka-broker /bin/bash
        - Switch to the kafka folder : cd opt/kafka/bin/
        - Turn first terminal to a producer, by :
        ./kafka-console-producer  --broker-list localhost:9092 --topic "processorBinding-in-0"
        - Turn second terminal to a consumer, by :
        ./kafka-console-consumer --bootstrap-server localhost:9092 --topic "processorBinding-out-0" --from-beginning

# TIPS:
#### Create a topic :
        ./kafka-topics.sh --create --topic publishOrder --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
#### List all message of a topic from beginnig :
        ./kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic <topic-name> --from-beginning
#### Delete a topic :
        ./kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic <topic-name>
        verify:
        ./kafka-topics.sh --bootstrap-server localhost:9092 --list
## API DOCS
http://localhost:8080/webjars/swagger-ui/index.html