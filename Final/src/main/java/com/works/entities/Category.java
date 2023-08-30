package com.works.entities;

import lombok.CustomLog;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;
    @NotNull
    @NotEmpty
    @Column(unique = true)
    private String category;
}
