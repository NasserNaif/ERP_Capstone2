package com.example.erp_system.ReposTsest;

import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.Employee;
import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.AuthRepo;
import com.example.erp_system.Repos.BranchRepo;
import com.example.erp_system.Repos.EmployeeRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmployeeRepoTest {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    AuthRepo authRepo;

    @Autowired
    BranchRepo branchRepo;

    Employee emp1;
    Employee emp2;
    Employee emp3;

    Employee testEmp;
    List<Employee> employees;

    Branch branch;

    User user;
    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        user = new User(1, "mamam", "123456", "nas@g.com", "waiting", "ADMIN", null);
        user1 = new User(2, "kaka", "123456", "nkk@g.com", "waiting", "ADMIN", null);
        user2 = new User(3, "lla", "123456", "lls@g.com", "waiting", "ADMIN", null);

        branch = new Branch(null, "olaya", 5000, 300, 400, 1000, null, null);

        emp1 = new Employee(1, "nas", 20000.0, "salesman", 10000, user, branch);
        emp2 = new Employee(2, "nas", 20000.0, "salesman", 10000, user1, branch);
        emp3 = new Employee(5, "nas", 20000.0, "salesman", 10000, user2, branch);
    }

    @Test
    public void findEmployeeByIdTest() {

        authRepo.save(user);
        employeeRepo.save(emp1);

        testEmp = employeeRepo.findEmployeeById(emp1.getId());

        Assertions.assertThat(testEmp.getId()).isEqualTo(emp1.getId());
    }

    @Test
    public void findAllByBranchTest() {
        branchRepo.save(branch);
        authRepo.save(user);
        authRepo.save(user1);
        authRepo.save(user2);

        employeeRepo.save(emp1);
        employeeRepo.save(emp2);
//        employeeRepo.save(emp3);


        employees = employeeRepo.findAllByBranch(branch);

        Assertions.assertThat(employees.get(0).getBranch().getId()).isEqualTo(branch.getId());

    }

}
