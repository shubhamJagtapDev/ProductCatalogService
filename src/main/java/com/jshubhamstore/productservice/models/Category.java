package com.jshubhamstore.productservice.models;

import lombok.Data;

@Data
public class Category extends BaseModel{
    private Long id;
    private String title;
}
