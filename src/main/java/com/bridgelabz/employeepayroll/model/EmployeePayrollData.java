package com.bridgelabz.employeepayroll.model;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "employee_payroll")
public @Data class EmployeePayrollData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "name")
    private String name;
    private double salary;
    private String gender;
    private LocalDate startDate;
    private String note;
    private String profilePic;

    @ElementCollection
    @CollectionTable(name = "employee_department", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "department")
    private List<String> departments;

    public EmployeePayrollData() {}

    public EmployeePayrollData(EmployeeDTO employeeDTO) {
        this.updateEmployeePayrollData(employeeDTO);
    }

    public void updateEmployeePayrollData(EmployeeDTO employeeDTO) {
//        this.employeeId = employeeId;
        this.name = employeeDTO.name;
        this.salary = employeeDTO.salary;
        this.gender = employeeDTO.gender;
        this.note = employeeDTO.note;
        this.startDate = employeeDTO.startDate;
        this.profilePic = employeeDTO.profilePic;
        this.departments = employeeDTO.departments;
    }


//    public EmployeePayrollData(EmployeeDTO employeeDTO) {
//        this.name = employeeDTO.getName();
//        this.salary = employeeDTO.getSalary();
//    }
//
//    public int getEmployeeId() {
//        return employeeId;
//    }
//    public String getName() {
//        return name;
//    }
//    public double getSalary(){
//        return salary;
//    }
//
//    public void setEmployeeId(int employeeId) {
//        this.employeeId = employeeId;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
}
