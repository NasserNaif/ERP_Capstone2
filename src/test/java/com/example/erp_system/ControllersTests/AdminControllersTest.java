package com.example.erp_system.ControllersTests;

import com.example.erp_system.Controllers.AdminController;
import com.example.erp_system.Models.User;
import com.example.erp_system.Services.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AdminController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class AdminControllersTest {

    @MockBean
    AdminService adminService;

    @Autowired
    MockMvc mockMvc;

    User user1, user2, user3;

    User userTest;

    List<User> userList;

    @BeforeEach
    void setUp() {
        user1 = new User(1, "nasser", "123456", "nass@g.com", "confirmed", "ADMIN", null);
        user2 = new User(2, "nasser1", "123456", "nas@g.com", "confirmed", "ADMIN", null);
        user3 = new User(3, "nasser2", "123456", "nss@g.com", "confirmed", "USER", null);


        userList = new ArrayList<>();

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

    }


    @Test
    public void getAllUserTest() throws Exception {
        when(adminService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get("/api/v1/admin/get-all-users")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username").value("nasser"));
    }


    @Test
    public void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/v1/admin/delete-user/{id}", user1.getId()))
                .andExpect(status().isOk());

    }

    @Test
    public void confirmUserTest() throws Exception {
        mockMvc.perform(put("/api/v1/admin/confirm-user/{id}", user1.getId()))
                .andExpect(status().isOk());

    }
}
