package com.example.erp_system.Services;

import com.example.erp_system.Models.User;
import com.example.erp_system.Repos.AuthRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServices {
    private final AuthRepo authRepo;

    public void register(User user) {
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        user.setRole("USER");
        user.setStatus("waiting");
        authRepo.save(user);
    }
    
}
