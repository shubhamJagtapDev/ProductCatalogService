package com.jshubhamstore.productcatalogservice.services;

import com.jshubhamstore.productcatalogservice.exceptions.ProductNotFoundException;
import com.jshubhamstore.productcatalogservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    Product addProduct(Product input);
}
