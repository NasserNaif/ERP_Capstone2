package com.example.erp_system.Services;

import com.example.erp_system.APIs.ApiException;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Repos.BranchRepo;
import com.example.erp_system.Repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BranchServices {

    private final BranchRepo branchRepo;
    private final EmployeeServices employeeServices;


    public List<Branch> getBranches() {
        return branchRepo.findAll();
    }

    public void addBranch(Branch newBranch) {
        branchRepo.save(newBranch);
    }

    public void updateBranch(Integer id, Branch newBranch) {
        Branch branch = branchRepo.findBranchById(id);

        if (branch != null) {
            branch.setName(newBranch.getName());
            branch.setRent(newBranch.getRent());
            branch.setRevenue(newBranch.getRevenue());
            branch.setUtilities(newBranch.getUtilities());
            branch.setBudget(newBranch.getBudget());
            branchRepo.save(branch);
        }
        throw new ApiException("wrong ID");
    }

    public void deleteBranch(Integer id) {
        Branch branch = branchRepo.findBranchById(id);
        if (branch != null)
            branchRepo.delete(branch);
        else
            throw new ApiException("wrong ID");
    }


    // find all the employees who work in specific branch
    public List<Employee> findEmployeesByBranchId(Integer branchId) {
        List<Employee> employees = employeeServices.findEmployeesByBranchId(branchId);

        if (employees != null)
            return employees;
        else
            throw new ApiException("wrong branch ID");
    }


    // count total expense
    public String totalExpense(Integer branchID) {
        Branch branch = branchRepo.findBranchById(branchID);


        branch.setBudget(branch.getBudget() - (branch.getRent() + branch.getUtilities()));
        branchRepo.save(branch);

        String message = "branch expense is : " + branch.getRent() + " Riyals, for rent, and : " + branch.getUtilities() + " Riyals, for utilities. " +
                "total is : " + (branch.getUtilities() + branch.getRent()) + " , new branch budget is : " + branch.getBudget();


        return message;
    }

    // raise salary func

    public void raiseSalary() {
        List<Branch> branches = branchRepo.raiseSalary();

        if (branches != null) {


            for (Branch b : branches
            ) {
                Employee emp = employeeServices.searchByBranchId(b.getId());

                if (emp.getPosition().equals("manager")) {
                    emp.setSalary(emp.getSalary() + (emp.getSalary() * 0.2));
                    employeeServices.updateEmployee(emp.getId(), emp);
                } else if (emp.getPosition().equals("salesman")) {
                    emp.setSalary(emp.getSalary() + (emp.getSalary() * 0.15));
                    employeeServices.updateEmployee(emp.getId(), emp);
                } else {
                    emp.setSalary(emp.getSalary() + (emp.getSalary() * 0.1));
                    employeeServices.updateEmployee(emp.getId(), emp);
                }
            }
        } else
            throw new ApiException("there's no branches sell more than 50K riyals this month.");
    }

}
