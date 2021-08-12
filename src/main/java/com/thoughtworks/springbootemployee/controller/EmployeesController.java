package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeesController {

    private final List<Employee> employees = new ArrayList<>();
    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping(params = "gender")
    public List<Employee> getAllEmployeesByGender(@RequestParam String gender) {
        return employeeService.getAllEmployeesByGender(gender);
    }
//
//    @GetMapping(params = {"pageIndex", "pageSize"})
//    public List<Employee> getEmployeesByPagination(@RequestParam Long pageIndex, @RequestParam Long pageSize) {
//        return employeeService.getEmployeesByPagination(pageIndex, pageSize);
//    }
//
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }
//
    @PutMapping(path = "/{employeeId}")
    public Employee updateEmployeeInformation(@PathVariable Integer employeeId, @RequestBody Employee employeeToBeUpdated) {
        return employeeService.updateEmployeeInformation(employeeId,employeeToBeUpdated);
    }
//
//    @DeleteMapping(path = "/{employeeId}")
//    public void deleteEmployee(@PathVariable Integer employeeId) {
//         employeeService.deleteEmployee(employeeId);
//    }

}
