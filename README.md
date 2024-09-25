# Book API

Book API is a Spring Boot-based RESTful service that provides comprehensive management of book information. It allows clients to perform CRUD operations, filter books based on various criteria, and rate books. The application is designed with scalability and maintainability in mind, featuring unit and integration tests to ensure reliability. Additionally, the application is containerized using Docker for easy deployment.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 17** or higher
- **Maven 3.8.x** or higher
- **Git** (for cloning the repository)
- **Docker** (optional, for containerization)

### Installation

1. **Clone the Repository**
    ```bash
    git clone https://github.com/VladimirDeriugin/book-api.git
    cd book-api
    ```

2. **Use Maven to build the project and download dependencies.**
    ```bash
    mvn clean install
    ```

## Running the Application

### 1. Using Maven
```bash
mvn spring-boot:run
```
2.Using the Executable Jar
java -jar target/book-api-0.0.1-SNAPSHOT.jar
3.Accessing the Application
The application will start on http://localhost:8080.
H2 Console: Accessible at http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:bookdb
Username: sa
Password: (leave blank)

Base URL (http://localhost:8080/api/books)

Testing
Running Tests
Execute the following command to run all tests:
mvn test

Using Docker
1.Build the Docker Image
docker build -t book-api:latest .
2.Run the Docker Container
docker run -d -p 8080:8080 --name book-api book-api:latest
3.Access the Application
http://localhost:8080
