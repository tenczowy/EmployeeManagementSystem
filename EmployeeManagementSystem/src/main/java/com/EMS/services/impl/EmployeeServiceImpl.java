package com.EMS.services.impl;

import com.EMS.entity.Employee;
import com.EMS.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import com.EMS.services.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findAllEmployee(String keyword) {
        if(keyword != null){
            return employeeRepository.searchAllByFirstName(keyword);
        }

        return (List<Employee>) employeeRepository.findAll();
    }
}
