package com.example.erp_system.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "username must not be empty")
    @Size(min = 3, message = "username must be more than 3 letters")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String username;

    @NotEmpty(message = "password must not be empty")
    @Size(min = 3, message = "password must be more than 3 letters")
    @Column(columnDefinition = "varchar(150) not null ")
    private String password;

    @NotEmpty(message = "password must not be empty")
    @Email(message = "email must be valid email pattern")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String email;


    @Column(columnDefinition = "varchar(10) not null check(status = 'waiting' or status = 'confirmed')")
    private String status;


    @Column(columnDefinition = "varchar(5) check(role= 'USER' or role= 'ADMIN') ")
    private String role;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Employee employee;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
