Challenge code

## Setup Instructions

### Prerequisites

- Java 17
- Spring Boot
- Maven
- Local instances of Anti-fraud service and Bank service (they can be fake services).

### Running the Application

1. **Clone the repository:**

    ```bash
    git clone <repository_url>
    cd payment-processing-service
    ```

2. **Configure the Application:**

    Ensure the `application. Properties`  file is correctly set up for your environment.

3. **Build the Project:**

    ```bash
    mvn clean install
    ```

4. **Run the Application:**

    ```bash
    mvn spring-boot:run
    ```

### API Endpoints

#### Purchase

- **URL:** `/api/payment/purchase`
- **Method:** `POST`
- **Request Body:**

    ```json
    {
        "cardNumber": "string",
        "amount": "double",
        "status": "string",
        "transactionType": "PURCHASE"
    }
    ```

- **Response:**

    ```json
    {
        "id": "long",
        "cardNumber": "string",
        "amount": "double",
        "status": "string",
        "transactionType": "PURCHASE"
    }
    ```

#### Refund

- **URL:** `/api/payment/refund`
- **Method:** `POST`
- **Request Body:**

    ```json
    {
        "cardNumber": "string",
        "amount": "double",
        "status": "string",
        "transactionType": "REFUND"
    }
    ```

- **Response:**

    ```json
    {
        "id": "long",
        "cardNumber": "string",
        "amount": "double",
        "status": "string",
        "transactionType": "REFUND"
    }
    ```

## Code Explanation

### `PaymentService`

Handles the core business logic for processing purchases and refunds.

### `PurchaseValidation`

A functional interface for validating credit card transactions.

### `ExternalServicePort`

An interface for interacting with external services (Anti-fraud and Bank services).

### `ExternalServicesRepository`

Implements the `ExternalServicePort` to communicate with external services using `RestTemplate`.

### `PaymentController`

Exposes the endpoints for processing purchases and refunds.

### `CreditCardTransaction`

Entity representing a credit card transaction.

## Additional Notes

- Ensure that the Anti-fraud and Bank services are running locally or accessible via the configured URLs.

