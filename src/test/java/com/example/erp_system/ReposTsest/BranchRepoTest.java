package com.example.erp_system.ReposTsest;


import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.BranchRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BranchRepoTest {

    @Autowired
    BranchRepo branchRepo;


    Branch branch;

    @BeforeEach
    void setUp() {
        branch = new Branch(null, "Olaya", 20000, 400, 300, 1000, null, null);
    }


    @Test
    public void findBranchByIdTest() {
        branchRepo.save(branch);

        Branch b = branchRepo.findBranchById(branch.getId());

        Assertions.assertThat(b).isEqualTo(branch);
    }


}
