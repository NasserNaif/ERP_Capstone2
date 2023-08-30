package com.example.erp_system.Services;

import com.example.erp_system.APIs.ApiException;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Models.Product;
import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.AuthRepo;
import com.example.erp_system.Repos.BranchRepo;
import com.example.erp_system.Repos.EmployeeRepo;
import com.example.erp_system.Repos.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor

public class BranchServices {

    private final BranchRepo branchRepo;
    private final EmployeeRepo employeeRepo;
    private final ProductRepo productRepo;
    private final AuthRepo authRepo;


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
        Branch branch = branchRepo.findBranchById(branchId);


        if (branch == null) {
            throw new ApiException("wrong branch ID");
        }

        List<Employee> employees = employeeRepo.findAllByBranch(branch);

        if (!employees.isEmpty())
            return employees;
        else
            throw new ApiException("sorry! there's no employees in this branch");
    }


    // count total expense
    public String totalExpense(Integer branchID) {
        Branch branch = branchRepo.findBranchById(branchID);

        if (branch == null) {
            throw new ApiException("sorry! there's no branch with this ID : " + branchID);
        }

        branch.setBudget(branch.getBudget() - (branch.getRent() + branch.getUtilities()));
        branchRepo.save(branch);

        String message = "branch expense is : " + branch.getRent() + " Riyals, for rent, and : " + branch.getUtilities() + " Riyals, for utilities. " +
                "total is : " + (branch.getUtilities() + branch.getRent()) + " , new branch budget is : " + branch.getBudget();


        return message;
    }

    // raise salary func

    public void raiseSalary(Integer id) {
        Branch branches = branchRepo.findBranchById(id);

        if (branches != null) {


            List<Employee> emps = employeeRepo.findAllByBranch(branches);


            System.out.println(emps);
            for (Employee e : emps
            ) {
                if (e.getPosition().equals("manager")) {
                    e.setSalary(e.getSalary() + (e.getSalary() * 0.2));
                    employeeRepo.save(e);

                } else if (e.getPosition().equals("salesman")) {
                    e.setSalary(e.getSalary() + (e.getSalary() * 0.15));
                    employeeRepo.save(e);
                } else {
                    e.setSalary(e.getSalary() + (e.getSalary() * 0.1));
                    employeeRepo.save(e);
                }
            }


        } else
            throw new ApiException("there's no branches sell more than 50K riyals this month.");
    }


    public String totalNetIncome() {
        int totalRevenue = 0;
        int totalExpense = 0;

        List<Branch> branches = branchRepo.findAll();

        for (Branch b : branches
        ) {
            totalRevenue += b.getRevenue();
            totalExpense += b.getUtilities() + b.getRent();
        }

        int total = totalRevenue - totalExpense;

        return "total net Income is : " + total;
    }

    public void addEmployee(Integer branchID, Integer employeeID) {
        Branch branch = branchRepo.findBranchById(branchID);
        Employee employee = employeeRepo.findEmployeeById(employeeID);

        if (branch == null) {
            throw new ApiException("sorry! there's no branch with this ID : " + branchID);
        } else if (employee == null) {
            throw new ApiException("sorry! there's no employee with this ID : " + employeeID);
        }


        employee.setBranch(branch);
        employeeRepo.save(employee);
    }

    public List<Product> getBranchProduct(Integer branchId) {
        Branch branch = branchRepo.findBranchById(branchId);


        if (branch == null) {
            throw new ApiException("wrong branch ID");
        }

        List<Product> products = productRepo.findAllByBranch(branch);

        if (!products.isEmpty())
            return products;
        else
            throw new ApiException("sorry! there's no employees in this branch");
    }


}
