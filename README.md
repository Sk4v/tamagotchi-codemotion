# What Dev-n-Ops can learn from a Tamagotchi
Codemotion speech 2024

Company: [Objectway SPA](https://www.objectway.com/)

Speaker: `Michele Danieli`, `Alessandro Scavino`

## Introduction
While innovation, new technologies and paradigms play an important role in our work as engineers, both on the application and platform side, the way our software grows, mature and behaves is a critical part. Nevertheless, it is an aspect that is often less cared for by many. Many factors contribute to being perceived as less important: customer pressure for new features on development, less budget on non-functional aspects, organization culture and silos and a good level of misunderstanding on the real cost of running code, when considering obsolescence and attritions for example. Like the Japanese game Tamagotchi, which required players to care for their creature, we must attentively nurture our software from inception to maturity and beyond. This is often the goal of iterative and continuous efforts to improve software maturity and practices. In this talk, we reflect on integrating operational aspects into our practices to achieve Technical Excellence.

## Repo Structure
This repository contains all the necessary components to set up and run the full application environment, including backend, frontend, monitoring, and testing. Below is an overview of the structure and purpose of each directory:

### `docker-compose/`
This directory contains all the Docker Compose files required to simulate the entire application environment. It includes:

- #### engine-compose.yml:
    - Sets up the application environment, including the backend, frontend, and supporting services.
- #### tamagotchi-compose.yml:
    - Sets up the monitoring and analysis stack, including Grafana, Prometheus, and SonarQube.

### `engine-be/`
This directory contains the backend project built with Java and Spring Boot. It is the core engine of the application, responsible for handling API requests, business logic, and database interactions.

### `engine-fe/`
This directory contains the frontend project built with Vue.js. It provides the user interface for interacting with the backend services and visualizing data.

### `postgres/`
This directory includes the necessary resources to build the Docker image for PostgreSQL, which is used as the database for the application.

### `k6/`
This directory contains the performance tests for the backend, written using k6. These tests are used to simulate load and measure the performance of the backend services.

### `api-call/`
This directory contains predefined API calls used with Bruno (an alternative to Postman) for testing and interacting with the backend APIs.

## Useful Resources

- [DevOps Topologies](https://web.devopstopologies.com/)

- [k6](https://k6.io/) | [k6 learn](https://github.com/grafana/k6-learn/tree/main)

- [PlantUML](https://plantuml.com/) | [PlantUML Guide](https://plantuml.com/guide)

- [Camunda](https://camunda.com/) | [Camunda 8 Docs](https://docs.camunda.io/docs/guides/) | [Camunda Getting started as a Java developer using Spring](https://docs.camunda.io/docs/guides/getting-started-java-spring/)

- [dependecy-track-maven-plugin](https://github.com/pmckeown/dependency-track-maven-plugin)