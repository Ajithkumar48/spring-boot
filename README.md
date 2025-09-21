# Spring Boot Demo Application

This is a simple Spring Boot application that demonstrates a RESTful API with two endpoints. It uses Maven for dependency management and includes Swagger for API documentation.

## Technologies Used

*   Java 17
*   Spring Boot 3.2.1
*   Maven
*   Spring Web
*   Spring Data JPA
*   H2 Database
*   SpringDoc OpenAPI (for Swagger UI)

## How to Build, Run, and Test

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

6.  **Run tests:**
    ```bash
    mvn test
    ```

The application will start on `http://localhost:8080`.

## API Endpoints

The base path for all endpoints is `/api`.

### Greeting API Endpoints

The base path for these endpoints is `/api/greeting`.

#### 1. GET /test

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

#### 2. POST /greet

*   **Description:** Greets a user by name.
*   **Method:** `POST`
*   **Parameters:**
    *   `name` (string, required): The name of the person to greet.
*   **Example Usage (using curl):**
    ```bash
    curl -X POST "http://localhost:8080/api/greeting/greet?name=Jules"
    ```
*   **Success Response:**
    *   **Code:** 200
    *   **Content:**
        ```json
        {
          "message": "Hello, Jules! This is a POST request."
        }
        ```

### Product API Endpoints

The base path for these endpoints is `/api/products`.

#### 1. GET /

*   **Description:** Get all products.
*   **Method:** `GET`
*   **Example Usage (using curl):**
    ```bash
    curl http://localhost:8080/api/products
    ```

#### 2. GET /{id}

*   **Description:** Get a product by ID.
*   **Method:** `GET`
*   **Example Usage (using curl):**
    ```bash
    curl http://localhost:8080/api/products/1
    ```

#### 3. POST /

*   **Description:** Create a new product.
*   **Method:** `POST`
*   **Example Usage (using curl):**
    ```bash
    curl -X POST http://localhost:8080/api/products \
    -H "Content-Type: application/json" \
    -d '{"name": "New Product", "price": 19.99}'
    ```

#### 4. PUT /{id}

*   **Description:** Update an existing product.
*   **Method:** `PUT`
*   **Example Usage (using curl):**
    ```bash
    curl -X PUT http://localhost:8080/api/products/1 \
    -H "Content-Type: application/json" \
    -d '{"name": "Updated Product", "price": 29.99}'
    ```

#### 5. DELETE /{id}

*   **Description:** Delete a product by ID.
*   **Method:** `DELETE`
*   **Example Usage (using curl):**
    ```bash
    curl -X DELETE http://localhost:8080/api/products/1
    ```

### User API Endpoints

The base path for these endpoints is `/api/users`.

#### 1. GET /

*   **Description:** Get all users.
*   **Method:** `GET`
*   **Example Usage (using curl):**
    ```bash
    curl http://localhost:8080/api/users
    ```

#### 2. GET /{id}

*   **Description:** Get a user by ID.
*   **Method:** `GET`
*   **Example Usage (using curl):**
    ```bash
    curl http://localhost:8080/api/users/1
    ```

#### 3. POST /

*   **Description:** Create a new user.
*   **Method:** `POST`
*   **Example Usage (using curl):**
    ```bash
    curl -X POST http://localhost:8080/api/users \
    -H "Content-Type: application/json" \
    -d '{"name": "Jules", "email": "jules@example.com"}'
    ```

#### 4. PUT /{id}

*   **Description:** Update an existing user.
*   **Method:** `PUT`
*   **Example Usage (using curl):**
    ```bash
    curl -X PUT http://localhost:8080/api/users/1 \
    -H "Content-Type: application/json" \
    -d '{"name": "Jules Verne", "email": "jules.verne@example.com"}'
    ```

#### 5. DELETE /{id}

*   **Description:** Delete a user by ID.
*   **Method:** `DELETE`
*   **Example Usage (using curl):**
    ```bash
    curl -X DELETE http://localhost:8080/api/users/1
    ```

### Order API Endpoints

The base path for these endpoints is `/api/orders`.

#### 1. GET /

*   **Description:** Get all orders.
*   **Method:** `GET`
*   **Example Usage (using curl):**
    ```bash
    curl http://localhost:8080/api/orders
    ```

#### 2. GET /{id}

*   **Description:** Get an order by ID.
*   **Method:** `GET`
*   **Example Usage (using curl):**
    ```bash
    curl http://localhost:8080/api/orders/1
    ```

#### 3. POST /

*   **Description:** Create a new order.
*   **Method:** `POST`
*   **Example Usage (using curl):**
    ```bash
    curl -X POST http://localhost:8080/api/orders \
    -H "Content-Type: application/json" \
    -d '{"userId": 1, "items": [{"productId": 1, "quantity": 2}]}'
    ```

#### 4. DELETE /{id}

*   **Description:** Delete an order by ID.
*   **Method:** `DELETE`
*   **Example Usage (using curl):**
    ```bash
    curl -X DELETE http://localhost:8080/api/orders/1
    ```

## API Documentation

This project uses SpringDoc to generate OpenAPI documentation. You can access the Swagger UI to view and interact with the API endpoints.

*   **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
*   **OpenAPI Spec (JSON):** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)
