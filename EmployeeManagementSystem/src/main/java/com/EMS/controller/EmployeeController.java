package com.EMS.controller;

import com.EMS.entity.Employee;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.EMS.services.EmployeeService;

import java.util.List;

@Controller
@RequestMapping("ems")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        super();
        this.employeeService = employeeService;
    }


    @GetMapping("/employees")
    public String listEmployees(Model model){
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.saveEmployee(employee);
        return "redirect:employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        return "edit_employee";
    }

    @PostMapping("/employees/{id}")
    public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model){
        Employee existingEmployee = employeeService.getEmployeeById(id);
        existingEmployee.setId(id);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        employeeService.updateEmployee(employee);
        return "redirect:/ems/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable Long id, Model model){
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        employeeService.deleteEmployeeById(id);
        return "redirect:/ems/employees";
    }

    @PostMapping("/employees/search/{keyword}")
    public String getEmployees(Model model, @PathVariable("keyword") String keyword){
        List<Employee> employees = employeeService.findAllEmployee(keyword);
        model.addAttribute("employees", employees);
        return "employees";
    }
}
