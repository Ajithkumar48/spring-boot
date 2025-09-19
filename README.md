# Spring Boot Demo Application

This is a simple Spring Boot application that demonstrates a RESTful API with two endpoints. It uses Maven for dependency management and includes Swagger for API documentation.

## Technologies Used

*   Java 17
*   Spring Boot 3.2.1
*   Maven
*   Spring Web
*   SpringDoc OpenAPI (for Swagger UI)

## How to Build and Run

1.  **Prerequisites:**
    *   Java 17 or later
    *   Maven

2.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```

3.  **Navigate to the project directory:**
    ```bash
    cd demo
    ```

4.  **Build the project:**
    ```bash
    mvn clean install
    ```

5.  **Run the application:**
    ```bash
    ./mvnw spring-boot:run
    ```

The application will start on `http://localhost:8080`.

## API Endpoints

The base path for all endpoints is `/api`.

### 1. GET /test

*   **Description:** A simple test endpoint that returns a greeting message.
*   **Method:** `GET`
*   **Success Response:**
    *   **Code:** 200
    *   **Content:**
        ```json
        {
          "message": "Greetings from Spring Boot!"
        }
        ```

### 2. POST /greet

*   **Description:** Greets a user by name.
*   **Method:** `POST`
*   **Parameters:**
    *   `name` (string, required): The name of the person to greet.
*   **Example Usage (using curl):**
    ```bash
    curl -X POST "http://localhost:8080/api/greet?name=Jules"
    ```
*   **Success Response:**
    *   **Code:** 200
    *   **Content:**
        ```json
        {
          "message": "Hello, Jules! This is a POST request."
        }
        ```

## API Documentation

This project uses SpringDoc to generate OpenAPI documentation. You can access the Swagger UI to view and interact with the API endpoints.

*   **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
*   **OpenAPI Spec (JSON):** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)
