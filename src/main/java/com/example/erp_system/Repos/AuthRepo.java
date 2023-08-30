package com.example.erp_system.Repos;

import com.example.erp_system.Models.Branch;
import com.example.erp_system.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepo extends JpaRepository<User, Integer> {
    User findUserById(Integer id);


//    List<User> findAllByBranch(Branch branch);

    @Query("select u from User u where u.username=?1 or u.email=?1")
    User logInUsernameOrEmail(String username);

}
