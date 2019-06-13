package com.ld.springboot_rest;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAO {
    private static Employees list = new Employees();

    static
    {
        list.getEmployeeList().add(new Employee(1,"Lokesh",
                "Gupta","howtodoinjava@gmail.com"));
        list.getEmployeeList().add(new Employee(2, "Alex",
                "Kolenchiskey", "abc@gmail.com"));
        list.getEmployeeList().add(new Employee(3, "David",
                "Kameron", "titanic@gmail.com"));
    }

    List<Employee> findByEmail(String email);

    public Employees getAllEmployees()
    {
        List<Employee> findByEmail(String email);
        return list;
    }

    public void addEmployee(Employee employee) {
        list.getEmployeeList().add(employee);
    }
}
