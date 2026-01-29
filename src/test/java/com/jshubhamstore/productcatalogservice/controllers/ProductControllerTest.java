package com.jshubhamstore.productcatalogservice.controllers;

import com.jshubhamstore.productcatalogservice.exceptions.ProductNotFoundException;
import com.jshubhamstore.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private IProductService productService;

    @Test
    public void testRuntimeExceptionThrow_FromProductService() throws ProductNotFoundException {
        when(productService.getSingleProduct(1L)).thenThrow(new RuntimeException("Manual exception thrown"));
        Exception exception = assertThrows(RuntimeException.class, () -> productController.getSingleProduct(1L));
        assertEquals("Manual exception thrown", exception.getMessage());
        verify(productService,times(0)).getSingleProduct(1L);
    }

    @Test
    public void testGetSingleProductById_WithNegativeId_ThrowsIllegalArgumentException() throws ProductNotFoundException {
        Long productId= -1L;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productController.getSingleProduct(productId));
        assertEquals("Invalid id passed, productId cannot be negative", exception.getMessage());
        // times when the function that you have mocked has return type as void, then in that case
        // we can assert on the no. of times that function was called
        verify(productService, times(0)).getSingleProduct(productId);
    }

}