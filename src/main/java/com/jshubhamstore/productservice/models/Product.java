package com.jshubhamstore.productservice.models;

import lombok.Data;

@Data
public class Product extends BaseModel{
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
}
