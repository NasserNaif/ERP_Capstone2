package com.example.erp_system.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "revenue must not be empty")
    @Positive(message = "revenue must be positive value")
    @Column(columnDefinition = "integer not null default 0")
    private Integer revenue;

    @NotNull(message = "rent must not be empty")
    @Positive(message = "rent must be positive value")
    @Column(columnDefinition = "integer not null")
    private Integer rent;


    @NotNull(message = "utilities must not be empty")
    @Positive(message = "utilities must be positive value")
    @Column(columnDefinition = "integer not null")
    private Integer utilities;


    @NotNull(message = "budget must not be empty")
    @Column(columnDefinition = "integer not null")
    private Integer budget;
}
