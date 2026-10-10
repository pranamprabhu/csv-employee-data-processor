package com.employeedata;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Run from the project root folder
        String inputPath = "data/employees.csv";
        String outputPath = "data/processed_employees.csv";
        
        CSVProcessor processor = new CSVProcessor();
        
        System.out.println("Reading and validating data from " + inputPath + "...\n");
        List<Employee> employees = processor.readEmployees(inputPath);
        
        System.out.println();
        processor.generateStatistics(employees);
        
        System.out.println();
        processor.writeProcessedData(employees, outputPath);
    }
}
