package com.example.erp_system.Controllers;


import com.example.erp_system.APIs.ApiResponse;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Services.EmployeeServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee/")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeServices employeeServices;

    @GetMapping("get")
    public ResponseEntity getAllEmp() {
        return ResponseEntity.status(200).body(employeeServices.getEmployees());
    }

    @PostMapping("add")
    public ResponseEntity addEmp(@RequestBody @Valid Employee newEmp) {
        employeeServices.addEmployee(newEmp);
        return ResponseEntity.status(201).body(new ApiResponse("employee added"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity updateEmp(@PathVariable Integer id, @RequestBody @Valid Employee newEmp) {
        employeeServices.updateEmployee(id, newEmp);
        return ResponseEntity.status(201).body(new ApiResponse("employee updated"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteEmp(@PathVariable Integer id) {
        employeeServices.deleteEmployee(id);
        return ResponseEntity.status(201).body(new ApiResponse("employee deleted"));
    }
}
