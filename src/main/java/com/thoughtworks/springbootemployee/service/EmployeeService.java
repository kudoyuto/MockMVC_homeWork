package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer employeeId) {

        return employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee id not found"));
    }

    public List<Employee> getAllEmployeesByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }
//
    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(pageIndex-1,pageSize)).getContent();
    }
//
    public Employee addEmployee(Employee employee) {
       return employeeRepository.save(employee);
    }
//
    public Employee updateEmployeeInformation(Integer employeeId, Employee employeeToBeUpdated) {
       Employee currentEmployeeInformation = getEmployeeById(employeeId);
       Employee updatedEmployeeInformation = updateEmployeeInfo(currentEmployeeInformation,employeeToBeUpdated);
       return employeeRepository.save(updatedEmployeeInformation);
    }
    public Employee updateEmployeeInfo(Employee employee, Employee employeeToBeUpdated) {
        if (employeeToBeUpdated.getName() != null) {
            employee.setName(employeeToBeUpdated.getName());
        }

        if (employeeToBeUpdated.getAge() != null) {
            employee.setAge(employeeToBeUpdated.getAge());
        }

        if (employeeToBeUpdated.getGender() != null) {
            employee.setGender(employeeToBeUpdated.getGender());
        }

        if (employeeToBeUpdated.getSalary() != null) {
            employee.setSalary(employeeToBeUpdated.getSalary());
        }
        return employee;
    }


    public void deleteEmployee(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
