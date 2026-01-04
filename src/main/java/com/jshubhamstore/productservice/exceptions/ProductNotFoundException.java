package com.jshubhamstore.productservice.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception {
    private Long productId;
    public ProductNotFoundException(Long productId) {
        this.productId = productId;
    }
}
