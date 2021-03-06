package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping(path = "/{companyId}")
    public Company getEmployeeById(@PathVariable Integer companyId) {
        return companyService.getCompanyByID(companyId);
    }

    @GetMapping(path = "/{companyId}/employees")
    public List<Employee> getCompanyEmployees(@PathVariable Integer companyId) {
        return companyService.getCompanyEmployees(companyId);
    }

    @GetMapping(params = {"pageIndex", "pageSize"})
    public List<Company> getCompanyByPagination(@RequestParam Long pageIndex, @RequestParam Long pageSize) {
        return companyService.getCompaniesByPagination(pageIndex, pageSize);
    }

    @PostMapping
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @PutMapping(path = "/{companyId}")
    public Company updateCompanyInformation(@PathVariable Integer companyId, @RequestBody Company companyToBeUpdated) {
        return companyService.updateCompanyInformation(companyId, companyToBeUpdated);
    }

    @DeleteMapping(path = "/{companyId}")
    public void deleteCompany(@PathVariable Integer companyId) {
        companyService.deleteCompany(companyId);
    }
}
