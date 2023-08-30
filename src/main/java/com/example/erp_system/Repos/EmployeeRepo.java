package com.example.erp_system.Repos;


import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);

//    Employee findEmployeeByBranchId(Integer id);

    List<Employee> findAllByBranch(Branch branch);


    List<Employee> findAllByUser(User user);


}
