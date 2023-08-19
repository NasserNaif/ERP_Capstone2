package com.example.erp_system.Repos;

import com.example.erp_system.Models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer> {
    Branch findBranchById(Integer id);


    @Query("select b from Branch b where b.revenue >= 50000")
    List<Branch> raiseSalary();
}
