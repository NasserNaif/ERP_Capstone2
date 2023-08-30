package com.example.erp_system.Services;

import com.example.erp_system.APIs.ApiException;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.AuthRepo;
import com.example.erp_system.Repos.BranchRepo;
import com.example.erp_system.Repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServices {

    private final EmployeeRepo employeeRepo;
    private final BranchRepo branchRepo;
    private final AuthRepo authRepo;


    public void addEmployee(Integer userID, Employee newEmployee) {

        User user = authRepo.findUserById(userID);

        if (user.getStatus().equalsIgnoreCase("waiting")) {
            throw new ApiException("sorry! your status isn't confirmed");
        }

        newEmployee.setUser(user);
        employeeRepo.save(newEmployee);
    }

    public void updateEmployee(Integer id, Employee newEmployee) {
        Employee employee = employeeRepo.findEmployeeById(id);

        if (employee != null) {
            employee.setName(newEmployee.getName());
            employee.setSalary(newEmployee.getSalary());
            employee.setPosition(newEmployee.getPosition());
            employee.setTotalSales(newEmployee.getTotalSales());
            employeeRepo.save(employee);
        }
        throw new ApiException("wrong ID----");
    }

    public void deleteEmployee(Integer id) {
        User employee = authRepo.findUserById(id);
        if (employee != null)
            authRepo.delete(employee);
        else
            throw new ApiException("you are not authorized to delete this employee ");
    }


}
