# SauceDemo Test Automation Framework

UI test automation framework for [SauceDemo](https://www.saucedemo.com/) built with Java, Selenium WebDriver, TestNG, Maven, Docker, Allure Report, and GitHub Actions.

The project automates core user flows of the application and demonstrates a structured approach to UI testing with smoke and regression separation, Docker-based execution, reporting, and CI integration.

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven
- Allure Report
- Docker
- Docker Compose
- GitHub Actions
- GitHub Pages

## Functionality

The framework includes automated tests for the following features:

- Login
- Logout
- Products page
- Product sorting
- Product details
- Cart
- Checkout

## Test Suites

### Smoke

Smoke tests cover the most critical user flow and are intended for quick validation of the application.

- minimal critical test set
- fast execution
- automatic run in GitHub Actions
- Docker-based execution

### Regression

Regression tests provide broader coverage of the application and include both positive and negative scenarios.

- wider functional coverage
- positive and negative test cases
- manual execution through GitHub Actions
- Allure report generation and publishing

## Project Architecture

The framework is built using the Page Object Model (POM) design pattern.

### Main structure

- pages — page objects with UI interaction logic
- tests — test scenarios grouped by feature
- core — driver creation and shared framework logic
- config — configuration-related code
- listener — failure handling and screenshot attachment logic
- .github/workflows — CI/CD workflows for smoke and regression execution

## Allure Report

Regression execution includes Allure reporting with:

- test descriptions
- severity levels
- feature grouping
- step reporting
- screenshots on failure

### View report locally

After running regression tests locally:

```bash
mvn allure:serve
````

View published report online:
- Live Allure Report: https://karina95459.github.io/qa-e2e-platform/

### Docker
Run smoke tests:
```bash
docker compose up --build
````
Run regression tests:
```bash
docker compose -f docker-compose.yml -f docker-compose.regression.yml up --build 
````
## GitHub Actions

### Smoke workflow

- runs automatically on push and pull request
- executes smoke tests in Docker
- provides quick validation of critical functionality

### Regression workflow

- runs manually via workflow_dispatch
- executes regression tests in Docker
- generates Allure report
- publishes report to GitHub Pages
