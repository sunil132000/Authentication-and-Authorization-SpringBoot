package com.example.security.service;

import com.example.security.jpa.entity.Employee;
import com.example.security.jpa.reposistory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public String getEmployee() {
        return "hello sunil";
    }
}
