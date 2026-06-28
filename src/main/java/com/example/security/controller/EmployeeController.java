package com.example.security.controller;

import com.example.security.jpa.entity.Employee;
import com.example.security.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;



    @PostMapping("/save")
    public Employee saveEmployee(@RequestBody Employee employee){
        System.out.println("save");
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/getEmployee")
    @PreAuthorize("hasAuthority('EMPLOYEE_READ')")
    public String  getEmployee(){
        System.out.println("get");
        return employeeService.getEmployee();
    }

}
