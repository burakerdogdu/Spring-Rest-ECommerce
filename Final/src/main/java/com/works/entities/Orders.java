package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long oid;
    @NotNull
    @NotEmpty
    private String productname;
    @NotNull
    private Long pid;

    @ManyToMany
    private List<User> users;
}
