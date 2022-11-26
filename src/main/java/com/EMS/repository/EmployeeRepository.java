package com.EMS.repository;

import com.EMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select * from employees e where e.first_name like %:keyword%", nativeQuery = true)
    List<Employee> findByKeyWord(@Param("keyword") String keyword);
}
