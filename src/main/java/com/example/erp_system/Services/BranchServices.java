package com.example.erp_system.Services;

import com.example.erp_system.APIs.ApiException;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Repos.BranchRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
@RequiredArgsConstructor

public class BranchServices {

    private final BranchRepo branchRepo;

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

}
