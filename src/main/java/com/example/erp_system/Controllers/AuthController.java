package com.example.erp_system.Controllers;


import com.example.erp_system.APIs.ApiResponse;
import com.example.erp_system.Models.User;
import com.example.erp_system.Services.AuthServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices authServices;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        authServices.register(user);
        return ResponseEntity.status(201).body(new ApiResponse("you register successfully"));
    }


    @GetMapping("/logout")
    public ResponseEntity logOut() {
        return ResponseEntity.status(200).body(new ApiResponse("you logged out successfully"));
    }

}
