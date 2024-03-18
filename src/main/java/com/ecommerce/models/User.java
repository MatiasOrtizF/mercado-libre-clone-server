package com.ecommerce.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "name is mandatory")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "name is mandatory")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "name is mandatory")
    @Email(message = "invalid email")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "name is mandatory")
    @Column(name = "password")
    private String password;
}
