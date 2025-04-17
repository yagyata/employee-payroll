package com.bridgelabz.employeepayroll.dto;

public class EmployeeDTO {

    private String name;
    private double salary;

    public EmployeeDTO() {}

    public EmployeeDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public double getSalary(){
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String toString() {
        return "name: " + name + ", " + "salary: " + salary;
    }
}
