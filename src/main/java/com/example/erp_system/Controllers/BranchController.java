package com.example.erp_system.Controllers;


import com.example.erp_system.APIs.ApiResponse;
import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Services.BranchServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/branch/")
@RequiredArgsConstructor
public class BranchController {

    private final BranchServices branchServices;

    @GetMapping("get")
    public ResponseEntity getAllBranches() {
        return ResponseEntity.status(200).body(branchServices.getBranches());
    }

    @PostMapping("add")
    public ResponseEntity addBranch(@RequestBody @Valid Branch newBranch) {
        branchServices.addBranch(newBranch);
        return ResponseEntity.status(201).body(new ApiResponse("branch added"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateBranch(@PathVariable Integer id, @RequestBody @Valid Branch newBranch) {
        branchServices.updateBranch(id, newBranch);
        return ResponseEntity.status(201).body(new ApiResponse("branch updated"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteBranch(@PathVariable Integer id) {
        branchServices.deleteBranch(id);
        return ResponseEntity.status(201).body(new ApiResponse("branch deleted"));
    }
}
