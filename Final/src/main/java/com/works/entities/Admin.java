package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aid;
    @Column(unique = true)
    @NotNull
    @NotEmpty
    private String username;
    @NotNull
    @NotEmpty
    private String password;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    @NotEmpty
    private String surname;
    @Column(unique = true)
    @Email
    @NotNull
    @NotEmpty
    private String email;

    @ManyToMany
    private List<Role> roles;

}
