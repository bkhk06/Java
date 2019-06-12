package com.ld.springboot_rest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeListRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByEmail(String email);
}
