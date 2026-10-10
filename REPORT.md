# Project Report: CSV Employee Data Processor

## 1. Introduction
The **CSV Employee Data Processor** is a Java-based application designed to read, validate, and process employee records from a CSV file. The primary objective of this project is to practice file processing, structured data handling, and building robust error-handling mechanisms to deal with malformed data.

## 2. Project Requirements
Based on the project prompt, the required deliverables were:
*   **CSV Reader:** A mechanism to read raw text data from an input file.
*   **Validation Logic:** Logic to ensure records meet specific integrity rules (e.g., no missing fields, correct data types).
*   **Employee Statistics:** Calculation of metrics across valid records.
*   **Output CSV:** Generation of a clean, processed output file.
*   **Resilience:** The application must handle malformed rows and not crash when encountering an invalid record.

## 3. Implementation Details

### 3.1 Architecture
The project follows an Object-Oriented design with clear separation of concerns:
*   **`Employee.java`**: Acts as a simple POJO (Plain Old Java Object) to store structured data (ID, Name, Department, Salary).
*   **`CSVProcessor.java`**: Encapsulates all the business logic. It handles the `FileReader` and `BufferedReader` for input, the validation rules during parsing, the calculation of statistics using standard Collections, and the `PrintWriter` for output.
*   **`Main.java`**: Serves as the entry point, defining the input and output file paths and invoking the processor.

### 3.2 Validation & Error Handling
Robust validation was implemented to catch errors gracefully:
*   Empty rows are rejected.
*   Records with missing columns (e.g., an ID and Name, but no Salary) throw an `IllegalArgumentException`.
*   Records with empty string values for critical fields like Name and Department are rejected.
*   Records with non-numeric strings in the Salary field (e.g., "invalid_salary") throw a `NumberFormatException`.

Instead of crashing the program, these exceptions are caught inside a `try-catch` block within a loop. The application simply logs the error indicating the exact line number and reason for failure, then proceeds to the next line.

### 3.3 Data Processing and Statistics
Valid records are collected into a `List<Employee>`. The statistics generation loops through this list to:
1.  Sum the total salaries to compute the average.
2.  Use a `HashMap<String, Integer>` to keep a running count of employees per department.

## 4. Test Results
The application was tested against a sample `employees.csv` file designed with deliberate errors (missing names, missing columns, and text inserted into numeric fields).

**Test Output Observation:**
*   The application successfully identified and logged errors for the malformed rows.
*   The application did not crash.
*   The valid 5 records were aggregated correctly.
*   The average salary and department breakdown were computed accurately.
*   A clean `processed_employees.csv` was successfully written to the disk.

## 5. Conclusion
The project was successfully completed, meeting all defined objectives. It demonstrates foundational skills in Java File I/O, Exception Handling, Data Structures (Collections), and basic Object-Oriented Design.
