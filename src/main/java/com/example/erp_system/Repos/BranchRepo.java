package com.example.erp_system.Repos;

import com.example.erp_system.Models.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer> {
    Branch findBranchById(Integer id);
}
