package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

//    @Test
//    void should_return_all_employees_when_get_all_employees_given_all_employees(){
//        // Given
//            List<Employee> employees = new ArrayList<>();
//            employees.add(new Employee(1, "Yuto", 23, "Male", 15000));
//            employees.add(new Employee(2, "JC", 22, "Male", 10000));
//            employees.add(new Employee(3, "Khali", 21, "Female", 10000));
//            employees.add(new Employee(4, "Donald", 21, "Male", 104400));
//            employees.add(new Employee(5, "Bob", 69, "Male", 10000));
//            employees.add(new Employee(6, "Zagu", 25, "Female", 16900));
//            given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
//
//        // When
//         List<Employee> actualEmployees = employeeService.getEmployees();
//
//        // Then
//        assertEquals(employees, actualEmployees);
//        assertEquals(employees.size(), actualEmployees.size());
//        assertIterableEquals(employees, actualEmployees);
//    }

    @Test
    void should_return_correct_employee_when_get_employee_by_id_given_an_employee_id(){
        // Given
        Employee employee = new Employee(1, "Yuto", 23, "Male", 15000);
        given(employeeRepository.findById(1)).willReturn(java.util.Optional.of((employee)));

        // When
        Employee actualEmployees = employeeService.getEmployeeById(1);

        // Then
        assertEquals(employee, actualEmployees);
    }

    @Test
    void should_return_male_employees_when_get_employee_by_gender_given_all_employees_and_male_as_gender(){
        // Given
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "Yuto", 23, "Male", 15000));
        employees.add(new Employee(2, "JC", 22, "Male", 10000));
        employees.add(new Employee(4, "Donald", 21, "Male", 104400));
        employees.add(new Employee(5, "Bob", 69, "Male", 10000));
        given(employeeRepository.findAllByGender("Male")).willReturn(employees);

        // When
        List<Employee> maleEmployees = employeeService.getAllEmployeesByGender("Male");

        // Then
        assertEquals(employees, maleEmployees);
        assertEquals(employees.size(), maleEmployees.size());
        assertIterableEquals(employees, maleEmployees);
    }
//
//    @Test
//    void should_return_correct_list_of_employees_by_Pagination_when_get_employees_by_pagination_given__all_employees_pageIndex_and_page_size(){
//        // Given
//        List<Employee> employees = new ArrayList<>();
//
//        employees.add(new Employee(6, "Zagu", 25, "Female", 16900));
//        given(retiringEmployeeRepository.getEmployees()).willReturn(employees);
//
//        // When
//        List<Employee> actualEmployees = employeeService.getEmployeesByPagination(1L, 5L);
//
//        // Then
//        assertIterableEquals(employees, actualEmployees);
//        assertEquals(employees.size(), actualEmployees.size());
//    }
//
    @Test
    void should_add_employee_when_add_Employee_given_a_new_employee(){
        // Given
        Employee employee = new Employee(7, "Bell", 69, "Male", 16900);

        // When
        employeeService.addEmployee(employee);

        // Then
        verify(employeeRepository, times(1)).save(employee);
    }
//
//    @Test
//    void should_update_employee_when_update_Employee_Information_given_an_updated_employee_information(){
//        // Given
//        Employee employee = new Employee(6, "Bell", 69, "Male", 16900);
//        given(retiringEmployeeRepository.updateEmployeeInformation(6, employee)).willReturn(employee);
//
//        // When
//        Employee updatedEmployee = employeeService.updateEmployeeInformation(6, employee);
//
//        // Then
//        assertEquals(employee, updatedEmployee);
//    }
//
//    @Test
//    void should_delete_employee_when_delete_Employee_Information_given_an_employee_id(){
//        // Given
//        Integer employeeId = 1;
//
//        // When
//        employeeService.deleteEmployee(employeeId);
//
//        // Then
//        verify(retiringEmployeeRepository, times(1)).deleteEmployee(employeeId);
//    }

}
