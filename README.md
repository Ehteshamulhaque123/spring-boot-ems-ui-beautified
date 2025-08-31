
# Employee Management System (Spring Boot)

This project is a web-based Employee Management System built using Spring Boot, Thymeleaf, and Bootstrap. It provides a user-friendly interface for managing employee records and includes the following functionalities:

## Features

- **Employee Listing:** View all employees in a paginated, searchable table.
- **Add Employee:** Create new employee records using a simple form.
- **Edit Employee:** Update details of existing employees.
- **Delete Employee:** Remove employees from the system with confirmation.
- **Export to CSV:** Download all employee data as a CSV file, with proper escaping for special characters.
- **Seed Database:** Quickly populate the database with sample employees for demo/testing.

## Technologies Used

- Spring Boot (Java)
- Thymeleaf (HTML templating)
- Bootstrap (responsive UI)

## How to Run

1. Make sure you have Java and Maven installed.
2. Run the application:
	- Using Maven: `mvn spring-boot:run`
	- Or run the `Application.java` class from your IDE.
3. Open your browser and go to [http://localhost:8080/employees](http://localhost:8080/employees)

## UI Overview

- **Employee List:** Search, sort, and paginate employees. Actions for edit, delete, and export are available.
- **Employee Form:** Add or edit employee details (name, department, salary).
- **Seed Button:** Instantly add sample employees if the database is empty.
- **Export Button:** Download all employee data in CSV format.

## API & Database

- **UI:** http://localhost:8080/employees
- **API:** http://localhost:8080/api/employees
- **H2 Console:** http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:emsdb`)

## Author

[Ehteshamul Haque](https://github.com/Ehteshamulhaque123)
