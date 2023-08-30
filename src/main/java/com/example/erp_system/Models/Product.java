package com.example.erp_system.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "price must not be null")
    @Positive(message = "price must be positive value")
    @Column(columnDefinition = "integer not null ")
    private Integer price;

    @NotNull(message = "quantity must not be null")
    @Positive(message = "quantity must be positive value")
    @Column(columnDefinition = "integer not null default 30")
    private Integer quantity;

    @NotNull(message = "sales must not be null")
    @Positive(message = "sales must be positive value")
    @Column(columnDefinition = "integer not null default 0")
    private Integer sales;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Branch branch;
}
