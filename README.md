# Demo Spring Security Project

This is a demo project showcasing the use of **Spring Security** with a custom authentication filter. The application demonstrates how to secure endpoints using a static API key passed in an HTTP header.

---

## Features
- Custom security filter for authentication.
- Static API key authentication.
- Configuration of the API key in application properties.
- Example endpoint secured with the custom filter.
- OpenAPI specification for the API.

---

## Prerequisites

- Java 17 or higher
- Gradle 7.x

---

## How It Works

### Custom Security Filter
The application implements a custom security filter to validate incoming requests using a static API key. The filter checks the presence and validity of the key in the `X-HelloApiKey` HTTP header.

### API Key Configuration
The static API key is stored in the application properties file under the key `service.api.key`. This allows you to change the key without modifying the code.

### Secured Endpoint
The `/api/hello` endpoint is secured using the custom filter. Only requests with a valid API key can access this endpoint.

### OpenAPI Specification
The API is documented using the OpenAPI standard. The specification is available in the `hello-api-spec.yml` file located at the root of the project. It describes the endpoint, request parameters, and responses in detail.

---

## Getting Started

### 1. Clone the Repository
```bash
git clone <repository_url>
cd <repository_directory>
```

### 2. Configure the API Key

Set the static API key in the `application.properties` file:

```properties
service.api.key=your-static-api-key
```

Replace `your-static-api-key` with the desired key value.

### 3. Build and Run the Application

Use Gradle to build and run the application:

```bash
./gradlew bootRun
```

The application will start on `http://localhost:8080`.

---

## Usage

### Test the Secured Endpoint
Send a GET request to the `/api/hello` endpoint with the API key in the `X-HelloApiKey` header:

```bash
curl -H "X-HelloApiKey: your-static-api-key" http://localhost:8080/api/hello
```

If the key is valid, the server will respond with:

```
Hello World!
```

If the key is missing or invalid, the server will return a `401 Unauthorized` response.

---

## Project Structure

- **`src/main/java`**: Contains application code, including the custom filter and secured controller.
- **`src/main/resources/application.properties`**: Configuration file for the API key.
- **`hello-api-spec.yml`**: OpenAPI specification for the API.
- **`build.gradle`**: Gradle build script.

---

## Learning Objectives
This project demonstrates:

1. How to implement a custom security filter in Spring Security.
2. Configuring and retrieving application properties.
3. Securing endpoints with custom authentication logic.
4. Using OpenAPI for API documentation.

---
