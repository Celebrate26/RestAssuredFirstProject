# RestAssuredFirstProject
-
A comprehensive API testing project using Rest Assured to automate user registration workflows on the Ndosi Automation platform.

## Table of Contents

- [Description](#description)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Test Cases](#test-cases)
- [Reporting](#reporting)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)


## Features

- **Admin Authentication**: Automated admin login to obtain authorization tokens
- **User Registration**: Create new user accounts with validation
- **Registration Approval**: Admin approval workflow for new registrations
- **User Authentication**: Verify user login functionality
- **User Cleanup**: Automated deletion of test users
- **Database Integration**: Fetch admin credentials from MySQL database
- **Comprehensive Reporting**: Allure reports with detailed test results
- **JSON Payload Handling**: Structured request/response handling

## Prerequisites

- **Java**: JDK 21 or higher
- **Maven**: 3.6 or higher
- **MySQL Connector**: For database connectivity (included in dependencies)
- **Internet Connection**: Required for API calls to https://ndosiautomation.co.za

## Installation

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd RestAssuredFirstProject
   ```

2. **Install dependencies**:
   ```bash
   mvn clean install
   ```

## Configuration

The project uses hardcoded configuration for the API endpoint and database connection. For production use, consider externalizing these values:

- **API Base URL**: `https://ndosiautomation.co.za`
- **Database**: MySQL connection to fetch admin credentials

**Note**: Database credentials are currently hardcoded. In a real-world scenario, use environment variables or configuration files.

## Usage

### Running Tests

Execute all tests:
```bash
mvn clean test
```

Run specific test class:
```bash
mvn test -Dtest=UserRegistration
```

### Generating Reports

1. **Generate Allure Report**:
   ```bash
   mvn allure:report
   ```

2. **Serve Allure Report**:
   ```bash
   mvn allure:serve
   ```

The report will be available at `http://localhost:8080` (or similar port).

## Test Cases

The project includes the following test scenarios in sequence:

1. **Admin Login Test** (`adminLoginTest`)
   - Logs in as admin using database credentials
   - Extracts authentication token for subsequent requests

2. **User Registration** (`userRegistration`)
   - Registers a new user with fake data using JavaFaker
   - Validates successful registration (201 status)

3. **Approve User Registration** (`approveUserRegistration`)
   - Admin approves the pending user registration
   - Uses admin token for authorization

4. **User Login Test** (`userLoginTest`)
   - Verifies the approved user can log in successfully
   - Confirms authentication works post-approval

5. **Delete User** (`deleteUser`)
   - Cleans up the test user using admin privileges
   - Ensures test data doesn't persist

## Reporting

The project uses Allure Framework for test reporting, providing:

- Detailed test execution results
- Step-by-step API request/response logs
- Screenshots and attachments (if configured)
- Historical trends and statistics
- Interactive web-based reports

## Project Structure

```
RestAssuredFirstProject/
├── src/
│   ├── test/
│       └── java/
│           ├── APIRequest/          # API request methods
│           ├── BaseURLs/            # Base URL configuration
│           ├── DBConnection/        # Database connection utilities
│           ├── Payload/             # JSON payload builders
│           └── UserRegistration/    # Main test class
├── allure-results/                  # Allure test results
├── target/                          # Maven build output
├── pom.xml                          # Maven configuration
└── README.md                        # This file
```

## Dependencies

- **Rest Assured 6.0.0**: Core API testing library
- **TestNG 7.11.0**: Testing framework
- **JSON Simple 1.1.1**: JSON parsing utilities
- **Gson 2.13.0**: JSON serialization/deserialization
- **Allure TestNG 2.29.1**: Test reporting
- **JavaFaker 1.0.2**: Fake data generation
- **MySQL Connector**: Database connectivity (via Maven)

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### Guidelines

- Follow existing code structure and naming conventions
- Add appropriate test cases for new features
- Update documentation as needed
- Ensure all tests pass before submitting


**Author:** Celebrate Mashaba
**Last Updated:** April 10, 2026

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Note**: This project connects to external services and databases. Ensure you have proper permissions and consider security implications when running tests against production environments.
