package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
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
    @NotNull
    @NotEmpty
    private String password;
    @Positive
    @Min(18)
    @NotNull
    private Integer age;
    @NotNull
    @NotEmpty
    private String adress;

    @ManyToMany
    private List<Role> roles;


}
