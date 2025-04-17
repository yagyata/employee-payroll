package com.bridgelabz.employeepayroll.model;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class EmployeePayrollData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
    private String name;
    private double salary;

    public EmployeePayrollData() {}

//    public EmployeePayrollData(int employeeId, String name, double salary) {
//        this.employeeId = employeeId;
//        this.name = name;
//        this.salary = salary;
//    }

    public EmployeePayrollData(int employeeId, EmployeeDTO employeeDTO) {
        this.employeeId = employeeId;
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
    }

    public EmployeePayrollData(EmployeeDTO employeeDTO) {
        this.name = employeeDTO.getName();
        this.salary = employeeDTO.getSalary();
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public String getName() {
        return name;
    }
    public double getSalary(){
        return salary;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }
}
