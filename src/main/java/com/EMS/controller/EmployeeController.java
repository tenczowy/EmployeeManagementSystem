package com.EMS.controller;

import com.EMS.entity.Employee;
import org.springframework.data.domain.Page;
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
        return findPaginated(1, model, "firstName", "asc");
    }

    @GetMapping("/employees/new")
    public String createEmployeeForm(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "create_employee";
    }

    @PostMapping("/employees")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model){
        if(employeeService.findByEmail(employee.getEmail()) == null){
            employeeService.saveEmployee(employee);
            return "redirect:employees";
        }
        employee.setEmail("Employee with this email already exists");
        model.addAttribute(employee);
        return "create_employee";

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

    @GetMapping("/employees/search")
    public String getEmployees(Model model,@Param("keyword")  String keyword){
        if(keyword != null){
            model.addAttribute("employees", employeeService.findByKeyword(keyword));
        }else {
            model.addAttribute("employees", employeeService.getAllEmployees());
        }

        return "employees";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,Model model, @RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir){
        int pageSize = 5;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> employees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("employees", employees);

        return "employees";
    }
}


















