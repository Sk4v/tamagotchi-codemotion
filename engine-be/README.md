# Suitability recommendation engine

## 1. **Introduction and Goals**

The **Suitability recommendation Engine** is designed to provide personalised ETF (Exchange Traded Fund) recommendations to users based on their investment preferences, such as risk profile, time horizon, and geographical region. The system integrates various components to process user input, evaluate business rules, and return results in real time.

### Key Features:

- **Personalized ETF Recommendations** based on risk profile, time horizon, and region.


### Key Quality Goals

Application must meet the following goals:
- be **Flexible** in the way it allows the definition and change og specific rules and thresholds
- be **Secure** to align to company guidelines for Secure Coding 
- be **Performant** the API is integrated into different channels and in particular TOL, so we need to ensure responsiveness 

## 2. **Context and Scope**

### System Context

The Suitability Engine interacts with multiple external systems, including:

```plantuml
@startuml C4_Context
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Context.puml
' uncomment the following line and comment the first to use locally
' !include C4_Context.puml

System(recommendation, "Suitability Recommendation Engine", "Recommends suitable products based on risk profile")

Person_Ext(user,"Investment Office Administrator", "Configure recommendation rules")

System_Ext(advisory, "Financial Advisory System", "Allows front-office to search for suitable products based on profile.")
System_Ext(tol, "Trading Online System", "Allows a trading system to search for product suitable to the profile.")
System_Ext(mdm, "Product Master Data", "Owns metadata of all products")


Rel(advisory, recommendation, "Uses")
Rel(user, recommendation, "Uses")
Rel(tol, recommendation, "Uses")
Rel(mdm,recommendation, "Feeds")

@enduml

```

## 3. **Solution Strategy**

The architecture uses a **microservice-style** approach with clear separation of concerns between the frontend, backend, business rule engine, and database.

| Quality goal | Scenario | Solution approach                 |
| :----------- | :------- | :-------------------------------- |
| `flexibility`| *Be able to modify and change the rules without changing the code* | **[Camunda]** Using Camunda modeler I define my DMN tables using FEEL and export them as .dmn files. I can evaluate my input based on my rules using Camunda zeebe.  |
| `performance`| *Understand how my application behaves under heavy workloads* | **[Grafana k6]**. Using k6 I can simulate differents loading jobs to prevents errors in production. I added k6 during my development process and into my GitLab CI pipeline for testing the impact of any pending code changes. |
| `security`   | *Detect the vulnerabilities inside my project dependecies* | **[Dependecy-check sonarqube plugin]**. Dependecy chek is a software composition analysis (SCA) tool that detect vulnerabilities contained in my project. The analysis is automated during my CI pipeline and I can immediately find if any vulnerabilities were introduced with my changes. |

### Key Technologies:

- **Vue.js** for the frontend.
- **Spring Boot 3.2.10** for the backend.
- **Camunda Zeebe** for decision-making.
- **PostgreSQL** for data storage.
- **Docker** for containerization.

## 4. **Building Blocks**

### 4.1 Overview

The system is divided into three main layers:

1. **Frontend Layer** (Vue.js): Collects user input and displays results.
2. **Backend Layer** (Spring Boot): Processes inputs, interacts with Camunda Zeebe and PostgreSQL.
3. **Data Layer** (PostgreSQL): Stores ETF data and user profiles.

### 4.2 Frontend (Vue.js)

- **Purpose**: Provides the user interface for investors to input preferences.
- **Technologies**: Vue.js, Element Plus for UI components.
- **Responsibilities**:
    - Input handling: risk profile, time horizon, and region.
    - Submission of the form to the backend.
    - Display of results returned by the backend.

### 4.3 Backend (Spring Boot)

- **Purpose**: Business logic handling and communication between the frontend, decision engine, and database.
- **Technologies**: Java 17, Spring Boot 3.2.10, REST API.
- **Responsibilities**:
    - Processing user input.
    - Requesting decisions from Camunda Zeebe.
    - Fetching relevant ETF data from the database.
    - Returning the final list of ETF recommendations to the frontend.

### 4.4 Camunda Zeebe

- **Purpose**: Process business rules and decision tables.
- **Responsibilities**:
    - Evaluate decision logic based on inputs (risk profile, time horizon, region).
    - Return decision results to the backend.

### 4.5 Database (PostgreSQL)

- **Purpose**: Persistent storage for ETF data.
- **Responsibilities**:
    - Store information about ETF products (name, ISIN, region, volatility, etc.).
    - Allow queries from the backend to retrieve ETF data based on decision results.

## 5. **Deployment View**

The application is containerized using **Docker Compose**  for easier deployment. The compose includes different componentsfor each service:

- **Frontend**: Vue.js container.
- **Backend**: Spring Boot application container.
- **Camunda Zeebe**: Camunda engine container.
- **PostgreSQL**: Database container.

```plantuml

@startuml
!define C4Deployment
!include https://raw.githubusercontent.com/plantuml-stdlib/C4-PlantUML/master/C4_Deployment.puml


title Deployment Diagram for Suitability Engine

Person_Ext(user,"Investment Office Administrator", "Configure recommendation rules")

Deployment_Node(docker_compose, "engine-compose", "Containerized Environment") {
    
  Container(frontend, "Frontend", "Container: engine-fe", "Provides user interface")
      
  Container(api, "Backend", "Container: engine-be", "Provides functionality via a REST API.")

  Container(camunda, "DMN Rules Engine", "Container: camunda/zeebe", "Manages DMN tables")    

  ContainerDb(db, "Database", "Relational Database Schema", "Stores products informations like Isin, name, volatility, etc.")   
}

System_Ext(master_data_manager, "Master Data Manager", "External System") {
}

BiRel(user, frontend, "Interacts", "Browser/HTTP")
BiRel(frontend, api, "API calls", "REST/HTTP")
BiRel(api, camunda, "Evaluate rules", "HTTP")
BiRel(api, db, "Reads and writes data", "JDBC")
Rel_R(master_data_manager, db, "Sends data to")

@enduml
```

## 6. **Quality Requirements**

Our application meets key quality requirements, including flexibility, security, and performance, to ensure robust and scalable operations.

1. **Flexibility:**
    - The application leverages **Camunda Zeebe** for flexible decision-making workflows, allowing dynamic configuration of rules and processes.
2. **Security:**
    - **Static Application Security Testing (SAST)** and **Software Composition Analysis (SCA)** using **SonarQube**  with the **Dependency-Check Plugin** and **Dependency Track**. 
    These tools ensure continuous identification and mitigation of vulnerabilities in both custom code and third-party dependencies.
3. **Performance:**
    - Performance testing is conducted using **k6** to simulate high-load scenarios and analyze the system’s behavior under stress.
    - Metrics related to system performance are continuously monitored using **Prometheus**, and visualized through **Grafana**, providing real-time insights into application health and performance bottlenecks.