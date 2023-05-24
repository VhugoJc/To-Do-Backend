# To-Do Backend App with Spring Boot and Maven

This is a backend application built with Spring Boot and Maven that provides API endpoints for managing a to-do list.

## Prerequisites

Make sure you have the following installed on your system:

- Java Development Kit (JDK) - version 17
- Apache Maven - version 3 or higher

## Getting Started

1. Clone the repository:

```bash
git clone https://github.com/VhugoJc/To-Do-Backend
```
2. Change to the project directory:

```bash
cd To-Do-Backend
```

Run the application:

```bash
mvn soring-boot:run
```
Test the application:
```bash
mvn test
```
The application will start running on http://localhost:9090.



## API Endpoints
The following API endpoints are available:

### Read To-Do Items
- **Endpoint**: `GET /api/todos`
- **Description**: Retrieves a list of all to-do items based on the parameters and filters sent.
- **Parameters**:
    - `name` (Optional): name or part of the name required (`string`).
    - `status` (optional): The status of the to-do items. Possible values: `done`, `undone`.
    - `priority` (optional): The priority of the to-do items. Possible values: `low`, `medium`, `high`.
    - `page` (optional): The page requerided (`int`). The page size is 10 items. 
- **Example Request 1**: `/api/todos`
- **Example Response 1**:
```JSON
{
    "totalPages": 1,
    "currentPage": 1,
    "toDos": [
        {
            "id": 1,
            "name": "task 1",
            "dueDate": "2023-05-18T10:08:47.316615",
            "doneDate": null,
            "createdDate": "2023-05-19T20:13:38.16397",
            "priotity": "medium",
            "done": false
        },
        {
            "id": 2,
            "name": "task 2",
            "dueDate": "2023-05-18T10:08:47.316615",
            "doneDate": "2023-05-19T20:14:14.568808",
            "createdDate": "2023-05-19T20:13:45.314997",
            "priotity": "low",
            "done": true
        },
        {
            "id": 3,
            "name": "task 3",
            "dueDate": "2023-05-18T10:08:47.316615",
            "doneDate": null,
            "createdDate": "2023-05-19T20:13:55.548158",
            "priotity": "high",
            "done": false
        }
    ]
}
```
- **Example Request 2**: `/api/todos?status=done&priority=low&name=task&page=1`
- **Example Response 2**:
```JSON
{
    "totalPages": 1,
    "currentPage": 1,
    "toDos": [
        {
            "id": 2,
            "name": "task 2",
            "dueDate": "2023-05-18T10:08:47.316615",
            "doneDate": "2023-05-19T20:19:36.987177",
            "createdDate": "2023-05-19T20:19:21.804428",
            "priotity": "low",
            "done": true
        }
    ]
}
```
In this example, the endpoint /api/todo accepts optional query parameters `status`, `priority`, `name` and `page` to filter the to-do items. The API will return a list of to-do items that match the provided filters.

### Create a To-Do Item
- **Endpoint**: `POST /api/todos`
- **Description**: Creates a new to-do item.
- **Example Request**:
```JSON
{
    "name":"task 1",
    "dueDate":"2023-05-18T10:08:47.316615",
    "priotity":"medium"
}
```
- **Example Response**:
```JSON
{
    "id": 1,
    "name": "task 1",
    "dueDate": "2023-05-18T10:08:47.316615",
    "doneDate": null,
    "createdDate": "2023-05-19T20:31:50.728214",
    "priotity": "medium",
    "done": false
}
```
### Update To-Do item
- **Endpoint**: `PUT /api/todos/{id}`
- **Description**: Updates an existing to-do item.
- **Example Request**: `/api/todos/1`
```JSON
{
    "name":"New Task",
    "dueDate":"2023-05-18T10:08:47.316615",
    "priotity":"low"
}
```
- **Example Response**:
```JSON
{
    "id": 1,
    "name": "New Task",
    "dueDate": "2023-05-18T10:08:47.316615",
    "doneDate": null,
    "createdDate": "2023-05-19T21:44:28.285446",
    "priotity": "low",
    "done": false
}
```

### Mark To-Do as done
- **Endpoint**: `POST /api/todos/{id}/done`
- **Description**: Changes `done` as true and assigns a date to `dueDate` by its ID.

### Mark To-Do as undone
- **Endpoint**: `PUT /api/todos/{id}/undone`
- **Description**: Changes `done` as false and assigns null to `dueDate` by its ID.

### Read Time Average
- **Endpoint**: `GET /api/metrics`
- **Description**: Retrieves time average of task with priority `low`, `medium` and `high`.
- **Example Request**: `/api/metrics`
- **Example Response**:
```JSON
{
    "highPriorityMinutes": 25.5,
    "lowPriorityMinutes": 25.5,
    "mediumPriorityMinutes": 25.5
}
```
### Delete To-Do Item
- **Endpoint**: `DELETE /api/todo/{id}`
- **Description**: Deletes a specific to-do item by its ID.

## Dependencies
The application uses the following dependencies:
- spring boot starter web
- spring boot devtools
- spring boot starter validation
- spring boot starter test

## Configuration
The application's configuration can be found in the `application.properties file` located in the `src/main/resources` directory. You can modify this file to change the server port.

## License
This project is licensed under the MIT License.

## Contributing
Contributions are welcome! Please feel free to submit a pull request with your improvements or suggestions.

