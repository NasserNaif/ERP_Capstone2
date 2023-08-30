package com.example.erp_system.Controllers;


import com.example.erp_system.APIs.ApiResponse;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Models.User;
import com.example.erp_system.Services.EmployeeServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServices employeeServices;

//    @GetMapping("get")
//    public ResponseEntity getAllEmp() {
//        return ResponseEntity.status(200).body(employeeServices.getEmployees());
//    }

    @PostMapping("add")
    public ResponseEntity addEmp(@AuthenticationPrincipal User user, @RequestBody @Valid Employee newEmp) {
        employeeServices.addEmployee(user.getId(), newEmp);
        return ResponseEntity.status(201).body(new ApiResponse("employee added"));
    }

    @PutMapping("update")
    public ResponseEntity updateEmp(@AuthenticationPrincipal User user, @RequestBody @Valid Employee newEmp) {
        employeeServices.updateEmployee(user.getId(), newEmp);
        return ResponseEntity.status(201).body(new ApiResponse("employee updated"));
    }

    @DeleteMapping("delete")
    public ResponseEntity deleteEmp(@AuthenticationPrincipal User user) {
        employeeServices.deleteEmployee(user.getId());
        return ResponseEntity.status(201).body(new ApiResponse("employee deleted"));
    }
}
