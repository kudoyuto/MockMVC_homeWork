package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.EmployeeRequest;
import com.thoughtworks.springbootemployee.model.EmployeeResponse;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeesController {


    private final List<Employee> employees = new ArrayList<>();
    private EmployeeService employeeService;
    private EmployeeMapper employeeMapper;

    public EmployeesController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping(path = "/{employeeId}")
    public EmployeeResponse getEmployeeById(@PathVariable Integer employeeId) {
        return employeeMapper.toResponse(employeeService.getEmployeeById(employeeId));
    }

    @GetMapping(params = "gender")
    public List<Employee> getAllEmployeesByGender(@RequestParam String gender) {
        return employeeService.getAllEmployeesByGender(gender);
    }
//
    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Employee> getEmployeesByPagination(@RequestParam Integer pageIndex, @RequestParam Integer pageSize) {
        return employeeService.getEmployeesByPagination(pageIndex, pageSize);
    }
//
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {

        return employeeMapper.toResponse(employeeService.addEmployee(employeeMapper.toEntity(employeeRequest)));
    }
//
    @PutMapping(path = "/{employeeId}")
    public EmployeeResponse updateEmployeeInformation(@PathVariable Integer employeeId, @RequestBody EmployeeRequest employeeRequest) {
        return employeeMapper.toResponse(employeeService.updateEmployeeInformation(employeeId,employeeMapper.toEntity(employeeRequest)));
    }
//
    @DeleteMapping(path = "/{employeeId}")
    public String deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return employeeMapper.toResponse();
    }

}
