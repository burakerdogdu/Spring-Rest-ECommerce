package com.works.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @NotEmpty
    @Length(min = 1, max = 150)
    @NotNull
    private String title;
    @NotEmpty
    @Length(min = 1, max = 500)
    @NotNull
    private String detail;


    @Positive
    @NotNull
    private Integer price;

    @NotNull
    private Integer stock;
    @NotEmpty
    @NotNull
    private String brand;

    @ManyToMany
    private List<Category> categories;
    @ManyToMany
    private List<Images> images;
}
