package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService{

    private RetiringEmployeeRepository retiringEmployeeRepository;

    public EmployeeService(RetiringEmployeeRepository retiringEmployeeRepository) {
        this.retiringEmployeeRepository = retiringEmployeeRepository;
    }

    public List<Employee> getEmployees() {
        return retiringEmployeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer employeeId) {
        return retiringEmployeeRepository.findById(employeeId).orElse(null);
    }

    public List<Employee> getAllEmployeesByGender(String gender) {
        return retiringEmployeeRepository.findAllByGender(gender);
    }
//
    public List<Employee> getEmployeesByPagination(Integer pageIndex, Integer pageSize) {
        return retiringEmployeeRepository.findAll(PageRequest.of(pageIndex-1,pageSize)).getContent();
    }
//
    public Employee addEmployee(Employee employee) {
       return retiringEmployeeRepository.save(employee);
    }
//
    public Employee updateEmployeeInformation(Integer employeeId, Employee employeeToBeUpdated) {
       Employee currentEmployeeInformation = getEmployeeById(employeeId);
       Employee updatedEmployeeInformation = updateEmployeeInfo(currentEmployeeInformation,employeeToBeUpdated);
       return retiringEmployeeRepository.save(updatedEmployeeInformation);
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

//
//    public void deleteEmployee(Integer employeeId) {
//        retiringEmployeeRepository.deleteEmployee(employeeId);
//    }
}
