# Pipeline Setup for RestAssured API Testing Project

This document outlines the CI/CD pipeline setup for the RestAssuredFirstProject, which automates the execution of API tests using GitHub Actions.

## Overview

The project uses:
- **Java 21** with Maven for build management
- **RestAssured** for API testing
- **TestNG** as the test framework
- **Allure** for test reporting
- **GitHub Actions** for CI/CD

## Pipeline Configuration

The pipeline is defined in `.github/workflows/rest-assured-tests.yml` and includes the following steps:

### Triggers
- Push to `main` branch
- Pull requests to `main` branch
- Manual trigger via `workflow_dispatch`

### Jobs
- **test**: Runs on `ubuntu-latest` with a 30-minute timeout

### Steps
1. **Checkout code**: Uses `actions/checkout@v4`
2. **Set up Java 21**: Uses `actions/setup-java@v4` with Temurin distribution and Maven caching
3. **Create testng.xml dynamically**: Generates a TestNG suite file specifying the `UserRegistration.UserRegistration` test class
4. **Check DB Reachability**: Verifies connection to database at `102.222.124.22:3306` (optional step)
5. **Build and Run Tests**: Executes `mvn clean test -Dsurefire.suiteXmlFiles=testng.xml`
6. **Upload TestNG Reports**: Saves `target/surefire-reports/` as an artifact
7. **Upload Allure Results**: Saves `allure-results/` as an artifact

## Test Configuration

- Tests are run in parallel with 2 threads
- Allure listener is attached for reporting
- The dynamic `testng.xml` overrides the static `testing.xml` in the repository

## Artifacts

After test execution, the following artifacts are uploaded:
- `testng-reports`: TestNG HTML reports
- `allure-results`: Allure test results for generating reports

## Local Setup

To run tests locally:
1. Ensure Java 21 and Maven are installed
2. Run `mvn clean test` or `mvn clean test -Dsurefire.suiteXmlFiles=testing.xml`

## Notes

- The pipeline uses `continue-on-error: true` for the test step to ensure artifacts are uploaded even if tests fail
- Database reachability check is included but may need adjustment based on your environment
- Allure reports can be generated locally using `allure serve allure-results/`