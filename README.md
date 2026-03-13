# My Bank Demo App

A Spring Boot microservice for bank account signup.

## Technologies Used
- Spring Boot
- Microservice architecture
- JPA (Java Persistence API)
- Java 17
- Maven
- JUnit for testing
- MySQL database

## Prerequisites
- Java 17
- Maven
- MySQL

## Setup
1. Clone the repository.
2. Create a MySQL database named `my_bank_db`.
3. Build the project: `mvn clean install`
4. Run the SQL script in `sql/init.sql` to initialize the database (optional, as JPA handles schema).

## Running the Application

### Default (Local Environment)
```bash
mvn spring-boot:run
```

### Specific Environment
Run with specific profile (local, dev, qa, or prod):
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

Or set environment variables:
```bash
export SPRING_PROFILES_ACTIVE=dev
mvn spring-boot:run
```

The application will start on port 8080.

## API Documentation

Access the Swagger UI documentation at:
```
http://localhost:8080/swagger-ui.html
```

Or view the OpenAPI specification at:
```
http://localhost:8080/v3/api-docs
```

**Note:** Swagger UI is disabled in production environment for security.

## Logging

### Log Configuration
Logs are configured differently for each environment:

- **Local**: DEBUG level for application, logs to console and file
- **Dev**: INFO level for application, logs to console and file
- **QA**: WARN level for root, INFO for application, logs to file
- **Prod**: WARN level, minimal logging, logs to `/var/log/my-bank-demo-app/`

### Log Files
Log files are stored in the `logs/` directory with the following retention policy:
- Max file size: 10MB (100MB in prod)
- Max history: 30 days (60 days in prod)

## Testing

### POST /api/signup
Creates a new account.

**Request Body:**
```json
{
  "firstName": "string",
  "lastName": "string",
  "email": "string",
  "mobile": "string"
}
```

**Response:**
```json
{
  "status": "SUCCESS",
  "data": {
    "id": 1,
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "mobile": "string",
    "accountNumber": "2010400xxxxx",
    "ifsc": "IFSC-xxxxx",
    "timestampUst": "2026-03-13T17:05:20",
    "timestampLocal": "2026-03-13T17:05:20"
  },
  "error": null,
  "timestampUst": "2026-03-13T17:05:20",
  "timestampLocal": "2026-03-13T17:05:20"
}
```

## Testing
- Use the Postman collection `MyBankDemoApp.postman_collection.json` to test the API.
- Run unit tests: `mvn test`

## Environment Configurations

The application supports multiple environments with specific configurations:

### Profiles Available
- **local**: Development environment on local machine
  - Database: localhost:3306
  - DDL: update (auto-creates/updates schema)
  - SQL logging: enabled
  - Debug logging enabled
  
- **dev**: Development server environment
  - Database: dev-db-server:3306
  - DDL: update
  - SQL logging: disabled
  - Info logging level
  
- **qa**: Quality Assurance environment
  - Database: qa-db-server:3306
  - DDL: validate (requires schema to exist)
  - SQL logging: disabled
  - Warning logging level
  
- **prod**: Production environment
  - Database: prod-db-server:3306
  - DDL: validate (requires schema to exist)
  - Connection pooling optimized
  - Minimal logging (Warning level)
  - Graceful shutdown enabled
  - Compression enabled
  - Database password from environment variable `DB_PASSWORD`

### Configuration Files
- `application.yaml` - Base configuration (all profiles)
- `application-local.yaml` - Local environment specific
- `application-dev.yaml` - Dev environment specific
- `application-qa.yaml` - QA environment specific
- `application-prod.yaml` - Production environment specific

## Generated Fields
- **Account Number**: Starts with 2010400 followed by 5 random digits (12 digits total).
- **IFSC**: IFSC- followed by 5 random digits.
- **Timestamps**: Current UTC time for timestamp_ust, current local time for timestamp_local.