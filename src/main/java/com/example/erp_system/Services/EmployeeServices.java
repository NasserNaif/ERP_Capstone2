package com.example.erp_system.Services;

import com.example.erp_system.APIs.ApiException;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServices {

    private final EmployeeRepo employeeRepo;

    public List<Employee> getEmployees() {
        return employeeRepo.findAll();
    }

    public void addEmployee(Employee newEmployee) {
        employeeRepo.save(newEmployee);
    }

    public void updateEmployee(Integer id, Employee newEmployee) {
        Employee employee = employeeRepo.findEmployeeById(id);

        if (employee != null) {
            employee.setName(newEmployee.getName());
            employee.setSalary(newEmployee.getSalary());
            employee.setPosition(newEmployee.getPosition());
            employee.setTotalSales(newEmployee.getTotalSales());
            employee.setBranchId(newEmployee.getBranchId());
            employeeRepo.save(employee);
        }
        throw new ApiException("wrong ID");
    }

    public void deleteEmployee(Integer id) {
        Employee employee = employeeRepo.findEmployeeById(id);
        if (employee != null)
            employeeRepo.delete(employee);
        else
            throw new ApiException("wrong ID");
    }

    public List<Employee> findEmployeesByBranchId(Integer branchId) {
        return employeeRepo.findAllByBranchId(branchId);
    }

    public Employee searchByBranchId(Integer id) {
        return employeeRepo.findEmployeeByBranchId(id);
    }

    
}
