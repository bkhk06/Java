package com.student.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DBUtils extends JpaRepository {
    public StudentForm findByAccount(String account);
}
