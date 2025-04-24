package com.bridgelabz.employeepayroll.service;


import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.exceptions.EmployeePayrollException;

import com.bridgelabz.employeepayroll.model.EmployeePayrollData;
import com.bridgelabz.employeepayroll.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeePayrollService implements IEmployeePayrollService {

    @Autowired
    private EmployeeRepository employeeRepository;

    private List<EmployeePayrollData> employeePayrollList = new ArrayList<>();

    @Override
    public List<EmployeePayrollData> getEmployeePayrollData() {
        return employeeRepository.findAll();
//        return employeePayrollList;
    }

    @Override
    public EmployeePayrollData getEmployeePayrollDataById(int empId) {
        return employeeRepository
                .findById(empId)
                .orElseThrow( ()-> new EmployeePayrollException("Employee with employeeId " + empId + " does not exist...!!"));
//        return employeePayrollList.stream()
//                .filter(empData -> empData.getEmployeeId() == empId)
//                .findFirst()
//                .orElseThrow(() -> new EmployeePayrollException("Employee Not Found"));

//        Optional<EmployeePayrollData> empData = repository.findById(String.valueOf(empId));
//        return empData.orElseThrow(() -> new RuntimeException("Employee not found with ID: " + empId));
    }

    @Override
    public List<EmployeePayrollData> getEmployeesByDepartment(String department) {
        return employeeRepository.findEmployeesByDepartment(department);
    }


    @Override
    public EmployeePayrollData createEmployeePayrollData(EmployeeDTO employeeDTO) {
        EmployeePayrollData empData = null;
        empData = new EmployeePayrollData(employeeDTO);
        log.debug("Emp Data: " + empData.toString());
//        employeePayrollList.add(empData);
        return employeeRepository.save(empData);
//        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
//        empData.setName(employeeDTO.name);

//        EmployeePayrollData empData = new EmployeePayrollData(empId, employeeDTO);
//        empData = new EmployeePayrollData(employeePayrollList.size()+1, employeeDTO);
//        employeePayrollList.add(empData);
//        return empData;
//        return repository.save(empData);
    }

    @Override
    public EmployeePayrollData updateEmployeePayrollData(int empId, EmployeeDTO employeeDTO) {
        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
        empData.updateEmployeePayrollData(employeeDTO);
        return employeeRepository.save(empData);
//        empData.setName(employeeDTO.name);
//        empData.setSalary(employeeDTO.salary);
//        employeePayrollList.set(empId-1, empData);
//        return empData;
//        return repository.save(empData);
    }

    @Override
    public void deleteEmployeePayrollData(int empId) {
        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
        employeeRepository.delete(empData);
//        employeePayrollList.remove(empId-1);
//        EmployeePayrollData empData = this.getEmployeePayrollDataById(empId);
//        repository.delete(empData);
    }

}
