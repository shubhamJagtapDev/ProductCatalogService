package com.jshubhamstore.productservice.services;

import com.jshubhamstore.productservice.exceptions.ProductNotFoundException;
import com.jshubhamstore.productservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
}
