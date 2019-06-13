package com.hawk.springbootmysql.repository;

import com.hawk.springbootmysql.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Liu.DA on 2019/6/13
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
