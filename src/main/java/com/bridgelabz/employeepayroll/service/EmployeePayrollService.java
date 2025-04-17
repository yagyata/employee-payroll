package com.bridgelabz.employeepayroll.service;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.model.EmployeePayrollData;
import com.bridgelabz.employeepayroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeePayrollService implements IEmployeePayrollService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return repository.findAll();
    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        Optional<EmployeePayrollData> empData = repository.findById(String.valueOf(empId));
        return empData.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + empId));
    }

    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeeDTO employeeDTO) {
        EmployeePayrollData empData = new EmployeePayrollData(employeeDTO);
        return repository.save(empData);
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeeDTO employeeDTO) {
        EmployeePayrollData existingEmp = this.getEmployeePayrollDataById(empId);
        existingEmp.setName(employeeDTO.getName());
        existingEmp.setSalary(employeeDTO.getSalary());
        return repository.save(existingEmp);
    }

    @Override
    public void deleteEmployeePayrollData(int empId) {
        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
        repository.delete(empData);
    }
}
