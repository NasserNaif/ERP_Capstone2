package com.example.erp_system.ReposTsest;


import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.AuthRepo;
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
public class AuthRepoTest {

    @Autowired
    AuthRepo authRepo;

    User user;

    User userTest;

    @BeforeEach
    void setUp() {
        user = new User(null, "mamam", "123456", "nas@g.com", "waiting", "ADMIN", null);
    }

    @Test
    public void findUserByIdTest() {
        authRepo.save(user);

        userTest = authRepo.findUserById(user.getId());

        Assertions.assertThat(userTest).isEqualTo(user);
    }

    @Test
    public void logInUsernameOrEmailTest() {
        authRepo.save(user);

        userTest = authRepo.logInUsernameOrEmail(user.getUsername());

        Assertions.assertThat(userTest.getUsername()).isEqualTo(user.getUsername());
    }
}
