# CarIQ

Project based on mock PetStore API using WireMock, Docker, Gradle, TestNG, RestAssured, OkHTTP.

## Requirements

- [Docker](https://www.docker.com/)
- [Java](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Gradle](https://gradle.org/)

## How to run
Create a docker container with WireMock server:
```bash
docker build -t cariq -f src/main/resources/Dockerfile .
docker run -p 8080:8080 -v $(pwd)/src/main/resources/mappings:/home/mappings cariq
```
Run tests:
```bash
./gradlew clean test
```
