package com.example.erp_system.ServicesTest;


import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.AuthRepo;
import com.example.erp_system.Services.AdminService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    AdminService adminService;


    @Mock
    AuthRepo authRepo;

    User user1;
    User user2;

    List<User> userList;


    @BeforeEach
    void setUp() {
        user1 = new User(null, "nasss", "123456", "nananana@g.com", "confirmed", "ADMIN", null);
        user2 = new User(null, "naaaa", "123456", "nananaddna@g.com", "waiting", "USER", null);

        userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

    }


    @Test
    public void getAllUsersTest() {
        when(authRepo.findAll()).thenReturn(userList);

        List<User> users = adminService.getAllUsers();

        Assertions.assertEquals(2, userList.size());

        verify(authRepo, times(1)).findAll();
    }


    @Test
    public void deleteUserTest() {
        when(authRepo.findUserById(user1.getId())).thenReturn(user1);

        adminService.deleteUser(user1.getId());

        verify(authRepo, times(1)).findUserById(user1.getId());
        verify(authRepo, times(1)).delete(user1);
    }

    @Test
    public void confirmUserTest() {
        when(authRepo.findUserById(user1.getId())).thenReturn(user1);

        adminService.confirmedUser(user1.getId());

        verify(authRepo, times(1)).findUserById(user1.getId());
        verify(authRepo, times(1)).save(user1);
    }
}
