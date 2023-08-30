package com.example.erp_system.Services;

import com.example.erp_system.APIs.ApiException;
import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AuthRepo authRepo;

    public List<User> getAllUsers() {
        return authRepo.findAll();
    }

    public void deleteUser(Integer id) {
        User user = authRepo.findUserById(id);

        if (user == null) {
            throw new ApiException("user doesn't exist");
        }

        authRepo.delete(user);
    }

    public void confirmedUser(Integer userID) {
        User user = authRepo.findUserById(userID);

        if (user == null) {
            throw new ApiException("user doesn't exist");
        }
        user.setStatus("confirmed");
        authRepo.save(user);
    }


}
