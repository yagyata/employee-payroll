package com.bridgelabz.employeepayroll.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

public @ToString class EmployeeDTO {
//    @NotEmpty(message = "Employee name cannot be null")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Employee name Invalid")
    public String name;

    @Min(value = 500, message = "Min wage should be more than 500")
    public double salary;

    @Pattern(regexp = "male|female", message ="Gender needs to be male or female")
    public String gender;

    @JsonFormat(pattern="dd MMM yyyy")
    @NotNull(message="startDate should Not be Empty")
    @PastOrPresent(message = "startDate should be past or today's date")
    public LocalDate startDate;

    @NotBlank(message = "Note cannot be empty")
    public String note;

    @NotBlank(message = "Profile pic cannot be empty")
    public String profilePic;

    @NotNull(message = "department should not be empty")
    public List<String> departments;

    public EmployeeDTO() {}

    public EmployeeDTO(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

//    public String getName() {
//        return name;
//    }
//    public double getSalary(){
//        return salary;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//    public void setSalary(double salary) {
//        this.salary = salary;
//    }
//
//    @Override
//    public String toString() {
//        return "name = " + name + ", " + "salary = " + salary;
//    }
}
