# k6 Performance Testing Suite

This repository contains performance tests for your API using [k6](https://k6.io/), an open-source load testing tool designed for testing the performance of APIs, microservices, and websites. k6 is lightweight, easy to use, and designed for automation.

## Directory Structure

- `script.js`: This file contains k6 test scripts that simulate different user behaviors and interactions with your API endpoints. 
- `scripts/start-k6.sh`: A shell script that automates the execution of k6 tests. You can run all the performance tests defined in `script.js` by executing this script.
- `scripts/generate-k6-test.sh`: This script, located in the `scripts` folder, uses `openapi-generator-cli.jar` to generate k6 test scripts automatically from your Swagger (OpenAPI) specification.
- `openapi-generator-cli.jar`: The JAR file used to convert your OpenAPI documentation into k6 test scripts automatically.

## Running the Tests

To execute the k6 tests, follow these steps:

1. Ensure you have k6 installed. You can install k6 by following the instructions here.
2. Run the following command in your terminal to execute the tests:
    
``` bash
./scripts/start-k6.sh
```
    
This will execute all the test cases defined in the `script.js` file.
    

## Generating k6 Tests from OpenAPI Specification

You can automatically generate k6 tests from your API's OpenAPI specification using `openapi-generator-cli.jar`. To do so, follow these steps:

1. Place your OpenAPI specification file (`openapi.json` or `openapi.yaml`) in the root directory.
2. Run the following command from the `scripts` directory to generate the k6 test script:
    
``` bash
./scripts/generate-k6-test.sh
```
    
This will use the `openapi-generator-cli.jar` tool to create a new k6 test script based on your OpenAPI specification.
    
## Resources
- [Load Testing Your API with Swagger/OpenAPI and k6](https://k6.io/blog/load-testing-your-api-with-swagger-openapi-and-k6/)
- [openapi-generator](https://github.com/OpenAPITools/openapi-generator#1---installation)