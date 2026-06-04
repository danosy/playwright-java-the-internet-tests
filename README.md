# Playwright Java Test Automation Framework

A test automation framework for UI and API testing, built with Java.

## Tech Stack

- **Playwright** 1.44 — UI automation
- **Rest Assured** 5.4 — API testing
- **JUnit 5** — test runner
- **Gradle** — build tool
- **Log4j2** — logging
- **Jackson** — JSON handling

## What This Framework Tests

This project targets [The Internet](https://the-internet.herokuapp.com) —
a practice site that covers common UI scenarios:

- Login / authentication flows
- Dynamic elements and waits
- Form interactions
- File upload and download
- Drag and drop
- Alerts and popups
- API endpoints via Rest Assured

## Project Structure

src/
├── main/java/
│   └── pages/          # Page Object Model classes
├── test/java/
│   ├── ui/             # UI tests (Playwright)
│   └── api/            # API tests (Rest Assured)

## How to Run

**Prerequisites:** Java 11+, Gradle

```bash
# Clone the repo
git clone https://github.com/danosy/playwright-java-the-internet-tests.git

# Run all tests
./gradlew test

# Run with parallel execution (3 threads)
./gradlew test --tests "*"
```

## Key Features

- Page Object Model for maintainable UI tests
- Parallel test execution (3 threads)
- REST API testing with request/response validation
- Structured logging with Log4j2
- Clean separation between UI and API test layers