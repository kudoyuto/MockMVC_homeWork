package com.thoughtworks.springbootemployee.IntegrationTest;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.repository.RetiringEmployeeRepository;
import net.minidev.json.JSONUtil;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RetiringEmployeeRepository employeeRepository;

    @AfterEach
    public void after() {
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_all_employees_when_call_get_employees_api() throws Exception {
        //given
        final Employee employee = new Employee("JC", 21, "Male", 999);
        employeeRepository.save(employee);
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("JC"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(999));
    }

    @Test
    void should_create_employee_when_call_create_employee_api() throws Exception {
        //given
        String employee = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"JC\",\n" +
                "        \"age\": 22,\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"salary\": 30000\n" +
                "    }";
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employee))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("JC"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value("30000"));
    }
    @Test
    void should_update_employee_when_call_update_employee_api() throws Exception {
        //given
        final Employee employee = new Employee("JC", 21, "Male", 999);
        final Employee savedEmployee = employeeRepository.save(employee);
        String employeeWithNewInfo = "{\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"JC\",\n" +
                "        \"age\": 22,\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"salary\": 30000\n" +
                "    }";
        //when
        //then
        int id = savedEmployee.getId();

        mockMvc.perform(MockMvcRequestBuilders.put("/employees/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeWithNewInfo))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("JC"))
                .andExpect(jsonPath("$.age").value("22"))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value("30000"));
    }
    @Test
    void should_return_all_employees_By_Gender_when_call_get_All_Employees_By_Gender_employees_api() throws Exception {
        //given
        Employee employee1 = new Employee("JC", 21, "Male", 999);
        Employee savedEmployee = employeeRepository.save(employee1);
        Employee employee2 = new Employee("Yuto", 22, "Male", 999);
        employeeRepository.save(employee2);


        //when
        //then
        String gender = savedEmployee.getGender();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/?gender={gender}",gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("JC"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(999))
                .andExpect(jsonPath("$[1].name").value("Yuto"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].gender").value("Male"))
                .andExpect(jsonPath("$[1].salary").value(999));
    }
    @Test
    void should_return_all_employees_by_pagination_when_call_get_All_Employees_By_page_index_and_page_size_api() throws Exception {
        //given
        Employee employee1 = new Employee("JC", 21, "Male", 999);
        Employee employee2 = new Employee("Yuto", 22, "Male", 999);
        Employee employee3 = new Employee("JCA", 21, "Male", 999);
        Employee employee4 = new Employee("Jeany", 23, "Female", 999);
        Employee employee5 = new Employee("Linbey", 24, "Female", 999);

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        employeeRepository.save(employee3);
        employeeRepository.save(employee4);
        employeeRepository.save(employee5);


        //when
        //then

        mockMvc.perform(MockMvcRequestBuilders.get("/employees?pageIndex=1&pageSize=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("JC"))
                .andExpect(jsonPath("$[0].age").value(21))
                .andExpect(jsonPath("$[0].gender").value("Male"))
                .andExpect(jsonPath("$[0].salary").value(999))
                .andExpect(jsonPath("$[1].name").value("Yuto"))
                .andExpect(jsonPath("$[1].age").value(22))
                .andExpect(jsonPath("$[1].gender").value("Male"))
                .andExpect(jsonPath("$[1].salary").value(999))
                .andExpect(jsonPath("$[2].name").value("JCA"))
                .andExpect(jsonPath("$[2].age").value(21))
                .andExpect(jsonPath("$[2].gender").value("Male"))
                .andExpect(jsonPath("$[2].salary").value(999))
                .andExpect(jsonPath("$[3].name").value("Jeany"))
                .andExpect(jsonPath("$[3].age").value(23))
                .andExpect(jsonPath("$[3].gender").value("Female"))
                .andExpect(jsonPath("$[3].salary").value(999))
                .andExpect(jsonPath("$[4].name").value("Linbey"))
                .andExpect(jsonPath("$[4].age").value(24))
                .andExpect(jsonPath("$[4].gender").value("Female"))
                .andExpect(jsonPath("$[4].salary").value(999));
    }
    @Test
    void should_return_specific_employees_by_id_when_call_get_Employees_By_id_api() throws Exception {
        //given
        Employee employee1 = new Employee("JC", 21, "Male", 999);
        employeeRepository.save(employee1);

        //when
        //then
        Integer savedEmployee = employee1.getId();
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/{id}", savedEmployee))
                .andExpect(jsonPath("$.name").value("JC"))
                .andExpect(jsonPath("$.age").value(21))
                .andExpect(jsonPath("$.gender").value("Male"))
                .andExpect(jsonPath("$.salary").value(999));


    }





}
