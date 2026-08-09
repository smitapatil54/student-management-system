# Student Management System

A simple console-based Java application for managing student records. This project is designed for a college **Maven + Jenkins Continuous Integration** practical.

## What This Project Does

The application lets you manage students in memory during runtime. You can add students, view all records, search by ID, and delete by ID through a text-based menu in the terminal.

No database or web framework is used. Data is stored in an `ArrayList` and is lost when the program closes.

## Features

- Add a student (ID, name, course, marks)
- View all students
- Search student by ID
- Delete student by ID
- Exit the application

## Project Structure

```
student-management-system/
├── pom.xml                          # Maven project configuration
├── README.md                        # Project documentation
├── .gitignore                       # Git ignore rules for Java/Maven
└── src/
    └── main/
        └── java/
            └── com/
                └── student/
                    ├── Main.java           # Console menu and user input
                    ├── Student.java        # Student model class
                    └── StudentService.java # Business logic and ArrayList storage
```

## How Maven Is Used

Maven is used for:

- **Project management** — defines project name, version, and dependencies
- **Compilation** — compiles Java 17 source code
- **Packaging** — creates an executable JAR file in the `target/` folder

Key settings in `pom.xml`:

- `groupId`: `com.student`
- `artifactId`: `student-management-system`
- `version`: `1.0`
- Java 17 compiler configuration
- JAR manifest with main class `com.student.Main`

## How to Build

Make sure Java 17 (or newer) and Maven are installed.

Run this command from the project root:

```bash
mvn clean package
```

If the build succeeds, Maven creates:

```
target/student-management-system-1.0.jar
```

## How to Run the JAR

After building, run:

```bash
java -jar target/student-management-system-1.0.jar
```

You will see a menu in the console. Enter a number (1–5) to choose an option.

## Jenkins Continuous Integration

Jenkins can automate build and verification every time code changes.

### Typical Jenkins Pipeline Steps

1. **Checkout** — Jenkins pulls the latest code from Git
2. **Build** — Jenkins runs `mvn clean package`
3. **Verify** — Jenkins checks that the build succeeds and the JAR is created
4. **Report** — Jenkins shows success or failure in the dashboard

### Example Jenkins Job Configuration

- **Source Code Management**: Git repository URL
- **Build Step**: Execute shell / Windows batch command:

  ```bash
  mvn clean package
  ```

- **Post-build**: Confirm `target/student-management-system-1.0.jar` exists

### Why This Project Is Good for CI Demo

- Standard Maven layout (easy for Jenkins to understand)
- Single command build (`mvn clean package`)
- Clear pass/fail result (BUILD SUCCESS or BUILD FAILURE)
- No external database setup required
- Small, readable codebase for classroom explanation

## Technologies Used

- Java 17
- Apache Maven
- Console I/O (`Scanner`)
- In-memory storage (`ArrayList`)

Jenkins Continuous Integration Practical
