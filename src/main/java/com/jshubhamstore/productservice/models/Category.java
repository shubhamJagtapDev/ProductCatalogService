package com.jshubhamstore.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

import java.util.List;

@Data
@Entity(name = "categories")
public class Category extends BaseModel{
    private String title;

    /*
    * We don't need to store the 1 to M relation of Category and Products in DB
    * Getting this type of relation in code is a valid requirement but not in DB
    * */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;
}