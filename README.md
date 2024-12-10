
# Rent Your Stuff

**Rent Your Stuff** is a microservices-based application designed to facilitate product rentals between users and product owners. 
The platform provides features like product management, order processing, payments, chat between users and owners, and user authentication.

## Features
- **User Management**: Register, login, and manage user profiles.
- **Product Management**: Add, update, and view products available for rent.
- **Order Management**: Place and manage rental orders.
- **Payment Processing**: Process payments for rental transactions.
- **Chat Service**: Communicate between users and product owners.
- **Service Gateway**: Centralized entry point for routing requests to various services.

---

## Tech Stack

- **Programming Language**: Java
- **Frameworks**: Spring Boot, Spring Cloud
- **Database**: Oracle Database
- **API Documentation**: Swagger/OpenAPI
- **Service Discovery**: Netflix Eureka
- **Dependencies**: Spring Web, Spring Data JPA, Spring Security

---

## Microservices

### 1. **Gateway Service**
   - Centralized gateway for routing API requests.
   - Port: `8080`

### 2. **Product Service**
   - Manage products available for rent.
   - Port: `8081`

### 3. **Order Service**
   - Handle rental orders.
   - Port: `8082`

### 4. **Payment Service**
   - Process payments for rental transactions.
   - Port: `8083`

### 5. **AppUser Service**
   - Manage user accounts and authentication.
   - Port: `8084`

### 6. **Chat Service**
   - Facilitate chat between users and product owners.
   - Port: `8085`

### 7. **Eureka Server**
   - Service registry for microservice discovery.
   - Port: `8761`

---

## Prerequisites

- **Java 17**
- **Maven 3.8+**
- **Oracle Database**
- **Postman** (Optional: For API testing)

---

## Installation & Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/rent-your-stuff.git
   cd rent-your-stuff
   ```

2. Set up the Oracle database:
   - Create the required tables for each service.
   - Import sample data from the provided SQL scripts.

3. Configure application properties:
   - Update database configurations in each service's `application.properties`.

4. Build and run each service:
   ```bash
   mvn clean install
   java -jar target/[service-name]-[version].jar
   ```

5. Start the Eureka Server:
   - Navigate to `http://localhost:8761` to view the service registry.

6. Start the Gateway:
   - Access the gateway at `http://localhost:8080`.

---

## API Documentation

- Swagger documentation is available for all services:
  - Gateway: `http://localhost:8080/swagger-ui.html`
  - Product Service: `http://localhost:8081/swagger-ui.html`
  - Order Service: `http://localhost:8082/swagger-ui.html`
  - Payment Service: `http://localhost:8083/swagger-ui.html`
  - AppUser Service: `http://localhost:8084/swagger-ui.html`
  - Chat Service: `http://localhost:8085/swagger-ui.html`

---

## Database Schema

### Users
| Column      | Type         |
|-------------|--------------|
| ID          | NUMBER(19)   |
| EMAIL       | VARCHAR(255) |
| PASSWORD    | VARCHAR(255) |
| USERNAME    | VARCHAR(255) |

### Products
| Column       | Type         |
|--------------|--------------|
| ID           | NUMBER(10)   |
| NAME         | VARCHAR(255) |
| DESCRIPTION  | VARCHAR(255) |
| PRICE        | FLOAT        |

### Orders
| Column       | Type         |
|--------------|--------------|
| ID           | NUMBER(19)   |
| ORDER_DATE   | TIMESTAMP    |
| PRODUCT_NAME | VARCHAR(255) |
| QUANTITY     | NUMBER(10)   |
| TOTAL_AMOUNT | FLOAT        |

### Payments
| Column        | Type         |
|---------------|--------------|
| ID            | NUMBER(19)   |
| AMOUNT        | FLOAT        |
| PAYMENT_DATE  | TIMESTAMP    |
| STATUS        | VARCHAR(255) |

### Chats
| Column        | Type         |
|---------------|--------------|
| ID            | NUMBER(19)   |
| USER_ID       | NUMBER(19)   |
| PRODUCT_ID    | NUMBER(19)   |
| OWNER_ID      | NUMBER(19)   |

### Messages
| Column      | Type         |
|-------------|--------------|
| ID          | NUMBER(19)   |
| CHAT_ID     | NUMBER(19)   |
| SENDER_ID   | NUMBER(19)   |
| CONTENT     | VARCHAR(255) |
| TIMESTAMP   | TIMESTAMP    |

---

## How to Contribute

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add your feature description"
   ```
4. Push to the branch:
   ```bash
   git push origin feature/your-feature
   ```
5. Create a pull request.

---

## License

This project is licensed under the [MIT License](LICENSE).

---

## Contact

For queries or feedback, reach out to:
- **Vishal Kumar**  
- [LinkedIn](https://linkedin.com/in/vvish-all)  
- [Email](mailto:vishal8mit@gmail.com)
