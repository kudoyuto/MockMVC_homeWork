package com.thoughtworks.springbootemployee.IntegrationTest;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private EmployeeRepository employeeRepository;



    @Test
    void should_return_employee_when_call_get_employee_by_company_id_api() throws Exception {
        //given

        Company twitterCompany = new Company("Twitter");
        Company savedCompany = companyRepository.save(twitterCompany);
        Integer companyId = savedCompany.getId();
        employeeRepository.save(new Employee( "yuta", 22, "female", 2000, companyId));
        employeeRepository.save(new Employee( "yuto", 21, "male", 1000, companyId));

        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/companies/{companyId}/employees", companyId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("yuta"))
                .andExpect(jsonPath("$[1].name").value("yuto"));
    }

}
