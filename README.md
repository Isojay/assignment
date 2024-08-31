# Assignment -  Item Purchase Management System

## Project Overview

This project is a Spring Boot application designed for managing the purchase of items in an organization. It enables users to handle multiple items in a single purchase, generating unique packet numbers and serial numbers for each item based on provided packing and quantity details.

### Key Features
- **Manage Item Details**: Track and manage items including name, unique code, quantity, packing type, and pack quantity.
- **Unique Identifiers Generation**: Generate unique packet numbers and serial numbers for items.
- **Task Queue and Notification**: Queue purchase tasks, process them sequentially, and notify stakeholders upon completion.

### Tools, Libraries, Frameworks, and Technologies

1. **Spring Boot**:
    - **Version**: 3.3.3
    - **Artifacts**:
        - `spring-boot-starter-data-jpa`: For Spring Data JPA and ORM support.
        - `spring-boot-starter-web`: For building web applications with RESTful services.
        - `spring-boot-starter-validation`: For bean validation with JSR-380 (Bean Validation 2.0).
        - `spring-boot-starter-mail`: For email sending capabilities.

2. **Java**:
    - **Version**: 17
    - The programming language used to develop the application.

3. **Lombok**:
    - **Artifact**: `lombok`
    - Provides annotations to reduce boilerplate code (e.g., getters, setters).

4. **PostgreSQL**:
    - **Artifact**: `postgresql`
    - **Scope**: Runtime
    - Database used for data persistence.

5. **JUnit**:
    - **Artifact**: `spring-boot-starter-test`
    - Testing framework included for unit and integration tests.

6. **SLF4J**:
    - Provides a simple facade for various logging frameworks (e.g., Logback, Log4j).

7. **Docker**:
    - Used for containerizing the application.

8. **Render**:
    - Platform for deploying the application and for the Database.

### Maven Build Plugins

1. **Spring Boot Maven Plugin**:
    - **Artifact**: `spring-boot-maven-plugin`
    - Used to package the application as an executable JAR or WAR file and to run Spring Boot applications.

## Project Setup

### Prerequisites

- Java 17
- Maven installed
- Docker installed (if using Docker for setup)
- PostgresSQL (if using Local Database)

### Installation

#### Using CLI

1. **Clone the Repository**

   ```bash
   git clone https://github.com/Isojay/assignment.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd assignment
   ```

#### Using Docker

1. **Build and Run the Project**

   ```bash
   docker compose build
   docker compose up
   ```

#### Using Maven

1. **Build the Project**

   ```bash
   mvn clean install
   ```

2. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

   Alternatively, you can run the JAR file:

   ```bash
   java -jar target/assignment-0.0.1-SNAPSHOT.jar
   ```
### Using IntelliJ IDEA

1. **Clone the Repository:**
    - **File > New > Project from Version Control > Git.**
    - Enter the repository URL and click **Clone**.

2. **Open the Project:**
    - IntelliJ auto-opens the project, or **File > Open** and select the directory.

3. **Import Maven Project:**
    - IntelliJ detects `pom.xml`, or right-click `pom.xml` > **Add as Maven Project**.

4. **Build the Project:**
    - **Maven tool window > Reload All Projects**, or **Build > Build Project**.

5. **Run the Application:**
    - Right-click the main class > **Run**, or use the terminal:
      ```bash
      mvn spring-boot:run
      ```
    - Alternatively, run the JAR:
      ```bash
      java -jar target/assignment-0.0.1-SNAPSHOT.jar
      ```



### Configuration

In project `ASSIGNMENT_DB_PASS` and `GOOGLE_APP_PASSWORD` are taken from environment variable soas no to expose the sensitive Data.


Update `application.properties` with your credentials:

- **Database**:
  ```properties
  spring.datasource.driver-class-name=org.postgresql.Driver
  spring.datasource.url=jdbc:postgresql://your-database-host/your-database-name
  spring.datasource.username=your-username
  spring.datasource.password=your-password
  ```

  **Instructions to Check Database Existence:**
   1. Log in to your PostgreSQL database using a tool like `psql` or a graphical client.
   2. Run the following SQL command to check if the database exists:
      ```sql
      SELECT datname FROM pg_database WHERE datname = 'your-database-name';
      ```
   3. If the database does not exist, create it using:
      ```sql
      CREATE DATABASE your-database-name;
      ```

- **Email (For Notification)**:
  ```properties
  spring.mail.host=smtp.gmail.com
  spring.mail.port=587
  spring.mail.username=your-email@gmail.com
  spring.mail.password=your-email-password
  ```

- **Logging**:
  ```properties
  logging.file.name=/opt/logs/spring-boot-application.log
  logging.level.root=INFO
  ```

Replace placeholders with your actual credentials.

## Demo

The project is deployed on Render using Docker. You can view the live demo and explore the API documentation using Swagger [here](https://assignment-y5o7.onrender.com/swagger-ui/index.html#/).

## Thank You.

##