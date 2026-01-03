package com.jshubhamstore.productservice.controllers;

import com.jshubhamstore.productservice.models.Product;
import com.jshubhamstore.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) {
        return productService.getSingleProduct(productId);
    }

    @GetMapping()
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return null;
    }

    /**
     *
     * @param productId - The product id for which we need to replace the product details
     * @param product - Product object with all updated product details
     * @return Replaced Product after saving in database
     */
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long productId,  @RequestBody Product product) {
        return null;
    }

    @DeleteMapping("/{id}")
    public Boolean removeProduct(@PathVariable("id") Long productId) {
        return false;
    }
}
