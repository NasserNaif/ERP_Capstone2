package com.example.erp_system.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name must not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "salary must not be null")
    @Positive(message = "salary must be positive value")
    @Column(columnDefinition = "double(10,2) not null default 500")
    private Double salary;

    @NotEmpty(message = "position must not be empty")
    @Pattern(regexp = "(manager|salesman|tea boy)")
    @Column(columnDefinition = "varchar(8) not null check(position = 'manager' or position = 'salesman' or position = 'tea boy')")
    private String position;

    @NotNull(message = "total sales must not be null")
    @Positive(message = "total sale must be positive value")
    @Column(columnDefinition = "integer not null default 0")
    private Integer totalSales;

    @NotNull(message = "branch ID must not be null")
    @Positive(message = "branch ID must be positive value")
    @Column(columnDefinition = "integer not null default 1")
    private Integer branchId;
}
