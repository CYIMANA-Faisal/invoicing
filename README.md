
# Spring Boot Application

This is a simple Spring Boot application developed using IntelliJ IDEA.

## Prerequisites

- Java Development Kit (JDK) installed (version 17 or higher)
- IntelliJ IDEA installed

## Getting Started

1. Clone the repository: `git clone <repository_url>`
2. Open IntelliJ IDEA
3. Navigate to `File > Open` and select the project directory
4. Wait for IntelliJ IDEA to import the project
5. Once imported, navigate to the main application class (usually `Application.java`)
6. Right-click on the main class and select `Run 'Application'`

## Features

- Describe key features or functionalities of your application here

## Configuration

- IYou need to have the application.properties
- spring.application.name=invoicing
  server.port=8005

#Security
spring.security.ignored=/**

# PostgreSQL datasource configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/invoicing_db
spring.datasource.username=
spring.datasource.password=

## Hibernate properties
spring.jpa.hibernate.ddl-auto=update

security.jwt.secret-key=3cfa76ef14937c1c0ea519f8fc057a80fcd04a7420f8e8bcd0a7567c272e007b
# 24h in milliseconds
security.jwt.expiration-time=86400000

# RabitMQ configuration
spring.rabbitmq.addresses = localhost:5672
