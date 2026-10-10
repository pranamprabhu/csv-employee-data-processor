# CSV Employee Data Processor

## Description
Read employee records from a CSV file, validate the data, calculate statistics, and generate a processed output file.

## Objective
Practice file processing and structured data handling in Java.

## Tools
* Java
* CSV
* File I/O

## Features & Deliverables
* **CSV Reader:** Reads raw employee data from `data/employees.csv`.
* **Validation Logic:** Checks for missing names, missing fields, and improperly formatted data.
* **Resilience:** Gracefully catches errors and skips invalid records so the program does not crash on malformed rows.
* **Employee Statistics:** Calculates and displays total valid records, average salary, and department breakdown.
* **Output CSV:** Generates a clean `data/processed_employees.csv` containing only valid records.

## Project Structure
```text
csv-employee-data-processor/
├── data/
│   ├── employees.csv              # Input dataset (contains some deliberate malformed rows for testing)
│   └── processed_employees.csv    # Output generated file (valid records only)
├── src/
│   └── com/
│       └── employeedata/
│           ├── CSVProcessor.java  # Core logic for reading, validating, and writing data
│           ├── Employee.java      # Employee data model (POJO)
│           └── Main.java          # Entry point
└── README.md
```

## How to Compile and Run

1. **Open a terminal** and navigate to the `csv-employee-data-processor` folder.
2. **Create the `bin` directory** (if it doesn't exist) for compiled classes:
   ```bash
   mkdir bin
   ```
3. **Compile the Java source files:**
   ```bash
   javac -d bin src/com/employeedata/*.java
   ```
4. **Run the program:**
   ```bash
   java -cp bin com.employeedata.Main
   ```

You will see output in your terminal listing the rows that failed validation, followed by the computed statistics. A new `processed_employees.csv` will be created in the `data/` folder.
