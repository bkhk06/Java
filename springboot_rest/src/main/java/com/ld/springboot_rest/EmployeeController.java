package com.ld.springboot_rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {
    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping(path = "/",produces = "application/json")
    public Employees getEmployees()
    {
        return employeeDAO.getAllEmployees();
    }

    @PostMapping(path= "/", consumes = "application/json", produces = "application/json")
    public org.springframework.http.ResponseEntity <Object> addEmployee(@RequestBody  Employee employee)
    {
        Integer id = employeeDAO.getAllEmployees().getEmployeeList().size() + 1;
        employee.setId(id);

        employeeDAO.addEmployee(employee);

        URI location = ServletUriComponentsBuilder .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(employee.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
