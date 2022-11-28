package com.EMS.service;

import com.EMS.entity.Employee;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();

    Employee saveEmployee(Employee employee);

    void deleteEmployeeById(Long id);

    Employee updateEmployee(Employee employee);

    Employee getEmployeeById(Long id);

    List<Employee> findByKeyword(String keyword);

    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
