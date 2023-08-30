package com.works.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long iid;
    @NotEmpty
    @NotNull
    private String path;
}
