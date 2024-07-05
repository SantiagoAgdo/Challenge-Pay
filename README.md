Payment Processing Service
Overview
This project implements a payment platform for processing purchases with credit cards. It includes validations, anti-fraud analysis, and interaction with a bank service to complete transactions. This first version (MVP) supports purchase and refund operations.

Requirements
Processes purchases only with credit cards.
Processes refund operations.
Stores important data for further processing or queries.
Consumes external services (Bank and Anti-fraud services).
Architecture
The project follows the Hexagonal Architecture pattern to separate business logic from external services and infrastructure concerns.

Folder Structure
lua
Copiar código
application.service
    └── PaymentService
domain
    └── models
        └── CommonProcessResult
        └── CommonValidationResult
    └── ports
        └── input
            └── CreditCardTransactionRepository
        └── output
            └── ExternalServicePort
infrastructure
    └── adapters
        └── input
            └── PaymentController
        └── output.repositories
            └── ExternalServicesRepository
    └── config
        └── AppConfig
    └── entities
        └── CreditCardTransaction
    └── validations
        └── PurchaseValidation
PayApplication
Context Diagram

Setup Instructions
Prerequisites
Java 17
Spring Boot
Maven
Local instances of Anti-fraud service and Bank service (they can be fake services).
Running the Application
Clone the repository:

bash
Copiar código
git clone <repository_url>
cd payment-processing-service
Configure the Application:

Ensure the application.properties or application.yml file is correctly set up for your environment.

Build the Project:

bash
Copiar código
mvn clean install
Run the Application:

bash
Copiar código
mvn spring-boot:run
API Endpoints
Purchase
URL: /api/payment/purchase

Method: POST

Request Body:

json
Copiar código
{
    "cardNumber": "string",
    "amount": "double",
    "status": "string",
    "transactionType": "PURCHASE"
}
Response:

json
Copiar código
{
    "id": "long",
    "cardNumber": "string",
    "amount": "double",
    "status": "string",
    "transactionType": "PURCHASE"
}
Refund
URL: /api/payment/refund

Method: POST

Request Body:

json
Copiar código
{
    "cardNumber": "string",
    "amount": "double",
    "status": "string",
    "transactionType": "REFUND"
}
Response:

json
Copiar código
{
    "id": "long",
    "cardNumber": "string",
    "amount": "double",
    "status": "string",
    "transactionType": "REFUND"
}
Code Explanation
PaymentService
Handles the core business logic for processing purchases and refunds.

PurchaseValidation
A functional interface for validating credit card transactions.

ExternalServicePort
An interface for interacting with external services (Anti-fraud and Bank services).

ExternalServicesRepository
Implements the ExternalServicePort to communicate with external services using RestTemplate.

PaymentController
Exposes the endpoints for processing purchases and refunds.

CreditCardTransaction
Entity representing a credit card transaction.

Additional Notes
Ensure that the Anti-fraud and Bank services are running locally or accessible via the configured URLs.
Error handling and logging can be enhanced as needed for production use.
Contributing
Fork the repository.
Create your feature branch (git checkout -b feature/your-feature).
Commit your changes (git commit -m 'Add some feature').
Push to the branch (git push origin feature/your-feature).
Create a new Pull Request.
License
This project is licensed under the MIT License.

Feel free to adjust paths, URLs, and other details to match your specific setup and requirements.
