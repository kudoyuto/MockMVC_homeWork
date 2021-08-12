package com.thoughtworks.springbootemployee.IntegrationTest;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

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
        //
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


}
