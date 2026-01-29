package com.jshubhamstore.productcatalogservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity(name = "products")
public class Product extends BaseModel{
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    private Category category;
}