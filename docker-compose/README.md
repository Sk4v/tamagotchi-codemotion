# Docker Compose Configuration

This directory contains Docker Compose files to facilitate the deployment and management of various services related to the suitability engine application and quality analysis tools.

## Files Overview

### 1. engine-compose.yml

The `engine-compose.yml` file defines the setup for the **Suitability Engine** application, which includes the following components:

- **Frontend**: running on port `3001`
- **Backend**: running on port `8080`
- **Camunda Zeebe**: running on port `5432`
- **PostgreSQL Database**: running on port `26500`

### Usage

To deploy the Suitability Engine, navigate to this directory and run:

```bash
docker-compose -f engine-compose.yml up
```

### 2. tamagotchi-compose.yml

The `tamagotchi-compose.yml` file includes all necessary services for **code quality analysis**. It contains:

- **SonarQube**: running on port `9000`
- **Prometheus**: running on port `9090`
- **Grafana**: running on port `3000`

```bash
docker-compose -f tamagotchi-compose.yml up
```

## Additional Information
**Sonarqube** use a custom docker image that include [dependecy-check plugin](https://github.com/dependency-check/dependency-check-sonar-plugin) defined on  `sonar/Dockerfile`.

**scripts** folder includes differents utility scripts for compose startup and clean container
