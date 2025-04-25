package com.bridgelabz.employeepayroll.controller;

import com.bridgelabz.employeepayroll.dto.EmployeeDTO;
import com.bridgelabz.employeepayroll.dto.ResponseDTO;
import com.bridgelabz.employeepayroll.model.EmployeePayrollData;
import com.bridgelabz.employeepayroll.service.IEmployeePayrollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeepayrollservice")
public class EmployeePayrollController {

    @Autowired
    private IEmployeePayrollService employeePayrollService;

    @RequestMapping(value = {"","/","/get"})
    public ResponseEntity<ResponseDTO> getEmployeePayrollData() {
        List<EmployeePayrollData> empDataList = null;
        empDataList = employeePayrollService.getEmployeePayrollData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful", empDataList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{empId}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("empId") int empId) {
        EmployeePayrollData empData = null;
        empData = employeePayrollService.getEmployeePayrollDataById(empId);
        ResponseDTO respDTO = new ResponseDTO("Get Call for ID Successful", empData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<ResponseDTO> getEmployeePayrollData(@PathVariable("department") String department) {
        List<EmployeePayrollData> empDataList = null;
        empDataList = employeePayrollService.getEmployeesByDepartment(department);
        ResponseDTO responseDTO = new ResponseDTO("Get Call for ID Successful", empDataList);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createEmployeePayrollData(@Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeePayrollData employeeData = null;
        employeeData = employeePayrollService.createEmployeePayrollData(employeeDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successfully", employeeData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<ResponseDTO> updateEmployeePayrollData(@PathVariable("empId") int empId,
                                                                 @RequestBody EmployeeDTO employeeDTO) {
        EmployeePayrollData employeeData = null;
        employeeData = employeePayrollService.updateEmployeePayrollData(empId, employeeDTO);
        ResponseDTO respDTO = new ResponseDTO("Updated Employee Payroll Data Successfully", employeeData);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
        employeePayrollService.deleteEmployeePayrollData(empId);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully", "Deleted id: " + empId);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }



//    @GetMapping("/get/{empId}")
//    public ResponseEntity<String> getEmployeePayrollData(@PathVariable("empId") int empId) {
//        return new ResponseEntity<String>("Get Call Success for id: " + empId, HttpStatus.OK);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<String> addEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO) {
//        return new ResponseEntity<String>("Created Employee Payroll Data for: " + employeeDTO, HttpStatus.OK);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<String> updateEmployeePayrollData(@RequestBody EmployeeDTO employeeDTO) {
//        return new ResponseEntity<String>("Updated Employee Payroll Data for: " + employeeDTO, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{empId}")
//    public ResponseEntity<String> deleteEmployeePayrollData(@PathVariable("empId") int empId) {
//        return new ResponseEntity<String>("Delete Call Success for id: " + empId, HttpStatus.OK);
//    }

}
