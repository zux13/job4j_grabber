# Job4j Grabber

## Project Description

**Job4j Grabber** is a job aggregator designed to scrape and collect Java developer job postings from various websites and store them in a database. The project includes features for flexible scheduling, easy extension to new job sources, and parallel site scraping.

## Features

- **Scheduled Execution**:
    - The application runs at a configurable interval (default: every minute).
    - Scheduling configuration is defined in the `app.properties` file.

- **Target Site**:
    - The initial implementation scrapes job postings from [career.habr.com](https://career.habr.com/vacancies/java_developer), focusing on the first 5 pages of Java-related vacancies.

- **Extensibility**:
    - Additional websites can be integrated into the system without modifying existing code.

- **Parallel Scraping**:
    - The application supports concurrent scraping of multiple sites for increased efficiency.

## Technologies Used

- **Maven**: Build automation and dependency management.
- **JaCoCo**: Code coverage tool for ensuring high test quality.
- **Checkstyle**: Enforces coding standards and ensures code consistency.

