# Multiplication Table

Console Java application that generates multiplication expressions based on values from `application.properties`.

The application supports different number types using the Strategy pattern. By default, it works with `int`, but it can also run with `long` and `double`.

## Features

- Reads `min`, `max`, and `increment` from `application.properties`
- Uses `int` as the default number type
- Supports `int`, `long`, and `double`
- Validates required properties and input values
- Generates multiplication expressions like `2 * 4 = 8`
- Builds an executable JAR with Maven
- Deploys and runs the JAR on EC2 using GitHub Actions

## Configuration

Configuration is stored in:

```text
src/main/resources/application.properties
```

Example:

```properties
min=1
max=10
increment=2
```

## Number Type

The number type is configured through a system property:

```bash
-Dnumber.type=double
```

If the property is not provided, the default type is `int`.

Supported values:

```text
int
long
double
```

## Run Locally

Build the project:

```bash
mvn clean package
```

Run with default `int` type:

```bash
java -jar target/MultiplicationTable-1.0.1.jar
```

Run with `long`:

```bash
java -Dnumber.type=long -jar target/MultiplicationTable-1.0.1.jar
```

Run with `double`:

```bash
java -Dnumber.type=double -jar target/MultiplicationTable-1.0.1.jar
```

## Tests

Run tests:

```bash
mvn test
```

## Build

Create executable JAR:

```bash
mvn clean package
```

The JAR file is created in:

```text
target/MultiplicationTable-1.0.1.jar
```

## Architecture

The project uses the Strategy pattern for number operations.

Main parts:

```text
config      - loading and validating application properties
strategy    - number type strategies
generator   - multiplication expression generation
model       - multiplication expression model
output      - console output
```

Adding a new number type is simple:

1. Create a new implementation of `NumberStrategy<T>`
2. Implement parsing, addition, multiplication, comparison, zero value, and formatting
3. Register the new strategy in `NumberStrategyFactory`

For example, a new `BigDecimalNumberStrategy` can be added without changing the generator logic.

## Deployment

Deployment is handled by GitHub Actions.

On push to `main`, the workflow:

1. Checks out the code
2. Sets up Java 21
3. Builds the JAR with Maven
4. Copies the JAR to EC2
5. Runs the JAR on EC2

Required GitHub repository secrets:

```text
EC2_HOST
EC2_USER
EC2_SSH_KEY
```

The JAR is copied to:

```text
/home/ec2-user/multiplication-table/app.jar
```

and then executed with Java 21.
