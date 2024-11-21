# Credit Microservice (microservicio03)

## Description

This microservice manages the credits requested by clients. It provides functionalities to calculate amounts, terms and payments, in addition to maintaining a history of credits granted. It allows customers to interact with their credit information in a secure and organized way.

## Pre - requisites

- **Java 20**
- **Maven**
- **Docker** (for Docker execution only)
- Red Docker compartida: `my-network`


## Initial Configuration

### Run Locally

#### Modify the file`bootstrap.yml`

Configure the active profile to use Docker:

```yaml
spring:
  profiles:
    active: local
```

#### Execute the Main Class

```yaml
mvn spring-boot:run
```

### Run Docker

#### Modify the file`bootstrap.yml`

Configure the active profile to use Docker:

```yaml
spring:
  profiles:
    active: docker
```
#### Compile and generate the Docker container
Build the Docker image with:

```yaml
docker build -t microservicio03:0.0.1-SNAPSHOT .
```

####  Run the microservice Docker container
Start the container with:

```yaml
docker run --name microservicio03 --network my-network -p 8083:8083 microservicio03:0.0.1-SNAPSHOT
```

## Notes:
- **If you need to change ports or other settings, edit the corresponding application.yml and Dockerfile files.**
- **To debug errors, check the container logs:**
    ```yaml
    docker logs microservicio03
    ``` 
- **Make sure that the Config Server and Registry Server are running before starting other services.**
