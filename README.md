# WordcloudProducer

## Run locally

Run `./gradlew bootRun` for a dev server. Server is located at `http://localhost:8080/`.

## Run in Docker

Run `docker compose up` for a Docker container that runs the application. Server is located at `http://localhost:8080/`.

## TODO list

- Add e2e tests. Need to research this, haven't written many tests in Java before, especially for Java web applications. Potential libraries: JUnit, REST Assured, Mockito.
- Refactor codebase to use Java best practices. For example, naming conventions.
- Add proper DTO classes abd validation for incoming payloads. Potential package to use: Hibernate validation.
- Add environment variables.
- Add production support.