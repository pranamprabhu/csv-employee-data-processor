package com.employeedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class CSVProcessor {

    public List<Employee> readEmployees(String filePath) {
        List<Employee> employees = new ArrayList<>();
        int lineNumber = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip header
            String header = br.readLine();
            lineNumber++;
            
            while ((line = br.readLine()) != null) {
                lineNumber++;
                try {
                    Employee emp = parseEmployee(line);
                    if (emp != null) {
                        employees.add(emp);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing row at line " + lineNumber + ": " + line);
                    System.err.println("  Reason: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        
        return employees;
    }

    private Employee parseEmployee(String line) throws IllegalArgumentException {
        if (line == null || line.trim().isEmpty()) {
            throw new IllegalArgumentException("Empty row");
        }
        
        String[] parts = line.split(",");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Missing fields, expected 4 but got " + parts.length);
        }
        
        int id = Integer.parseInt(parts[0].trim());
        String name = parts[1].trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        String department = parts[2].trim();
        if (department.isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty");
        }
        double salary = Double.parseDouble(parts[3].trim());
        
        return new Employee(id, name, department, salary);
    }
    
    public void generateStatistics(List<Employee> employees) {
        if (employees.isEmpty()) {
            System.out.println("No valid employees to process.");
            return;
        }
        
        double totalSalary = 0;
        Map<String, Integer> deptCount = new HashMap<>();
        
        for (Employee emp : employees) {
            totalSalary += emp.getSalary();
            deptCount.put(emp.getDepartment(), deptCount.getOrDefault(emp.getDepartment(), 0) + 1);
        }
        
        double avgSalary = totalSalary / employees.size();
        
        System.out.println("--- Employee Statistics ---");
        System.out.println("Total Valid Records: " + employees.size());
        System.out.printf("Average Salary: $%.2f%n", avgSalary);
        System.out.println("Department Breakdown:");
        for (Map.Entry<String, Integer> entry : deptCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue() + " employees");
        }
    }
    
    public void writeProcessedData(List<Employee> employees, String outputPath) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(outputPath))) {
            pw.println("ID,Name,Department,Salary");
            for (Employee emp : employees) {
                pw.printf("%d,%s,%s,%.2f%n", emp.getId(), emp.getName(), emp.getDepartment(), emp.getSalary());
            }
            System.out.println("Processed data written to " + outputPath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
