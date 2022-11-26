package com.EMS.repository;

import com.EMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public List<Employee>  searchAllByFirstName(String name);

}
