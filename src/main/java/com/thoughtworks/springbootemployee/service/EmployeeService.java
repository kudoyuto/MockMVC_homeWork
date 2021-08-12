package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
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
//    public List<Employee> getEmployeesByPagination(Long pageIndex, Long pageSize) {
//        return retiringEmployeeRepository.getEmployees().stream()
//                .skip((pageIndex - 1) * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
//
    public Employee addEmployee(Employee employee) {
       return retiringEmployeeRepository.save(employee);
    }
//
//    public Employee updateEmployeeInformation(Integer employeeId, Employee employeeToBeUpdated) {
//       return retiringEmployeeRepository.updateEmployeeInformation(employeeId, employeeToBeUpdated);
//    }
//
//    public void deleteEmployee(Integer employeeId) {
//        retiringEmployeeRepository.deleteEmployee(employeeId);
//    }
}
