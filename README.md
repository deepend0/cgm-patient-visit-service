# Patient Visit Service

This repository contains an application for managing visits and patients in a medical system.

## Controllers

### VisitController

The `VisitController` provides REST endpoints for managing visits.

#### Endpoints

- **GET /visit?patientId** - Retrieves visits of a patient.
- **GET /visit/{visitId}** - Retrieves a specific visit by ID.
- **POST /visit** - Creates a new visit.
- **PUT /visit** - Updates an existing visit.

### PatientController

The `PatientController` provides REST endpoints for managing patients.

#### Endpoints

- **GET /patient** - Retrieves all patients.

## Technologies Used

- Java
- Spring MVC
- JUnit 5
- Mockito

## Setup

To run application locally, make sure you have Java and Maven installed on your machine. Clone this repository and run the Spring Boot application.

```bash
git clone https://github.com/deepend0/cgm-patient-visit-service
cd cgm-patient-visit-service
mvn spring-boot:run
```

## Testing

Unit tests and integration tests are provided for both controllers. These tests ensure the correctness of controller behavior and error handling.

To run the tests, execute the following command:

```bash
mvn test
```

## Error Handling

The controllers handle various error scenarios, such as bad requests and not found errors, using Spring's @ControllerAdvice annotation in ExceptionHandlerControllerAdvice.


## Contributors

    Orkun Akile - orkun.akile@gmail.com