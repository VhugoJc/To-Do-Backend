# To-Do Backend App with Spring Boot and Maven

This is a backend application built with Spring Boot and Maven that provides API endpoints for managing a to-do list.

## Prerequisites

Make sure you have the following installed on your system:

- Java Development Kit (JDK) - version 8 or higher
- Apache Maven - version 3 or higher

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/your-username/todo-backend.git  
```
2. Change to the project directory:

```bash
cd todo-backend
```
Build the application using Maven:

    mvn clean install
Run the application:

    java -jar target/todo-backend-1.0.0.jar

The application will start running on http://localhost:8080.

## API Endpoints
The following API endpoints are available:

- Get All To-Do Items
- Endpoint: GET /api/todo
- Description: Retrieves a list of all to-do items. 
Example Response:
