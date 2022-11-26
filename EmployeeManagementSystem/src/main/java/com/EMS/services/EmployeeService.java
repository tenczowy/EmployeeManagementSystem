package com.EMS.services;

import com.EMS.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    void deleteEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> findAllEmployee(String keyword);
}
