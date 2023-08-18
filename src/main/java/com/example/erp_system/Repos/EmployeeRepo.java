package com.example.erp_system.Repos;


import com.example.erp_system.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);
}
