package com.jshubhamstore.productcatalogservice.controllers;

import com.jshubhamstore.productcatalogservice.dtos.ProductDto;
import com.jshubhamstore.productcatalogservice.models.Category;
import com.jshubhamstore.productcatalogservice.models.Product;
import com.jshubhamstore.productcatalogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetAllProducts_RunSuccessfully() throws Exception {
        // Arrange
        Product product1 = new Product();
        product1.setId(3L);
        product1.setTitle("MacBook Pro M3 Pro");
        product1.setPrice(230000D);

        Product product2 = new Product();
        product2.setId(4L);
        product2.setTitle("MacBook Pro M5 Pro");
        product2.setPrice(280000D);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productService.getAllProducts()).thenReturn(productList);

        // TODO Current impl returns list of products however in next iteration we need to return ProductDtos instead
//        ProductDto productDto1 = new ProductDto();
//        productDto1.setId(3L);
//        productDto1.setTitle("MacBook Pro M3 Pro");
//        productDto1.setPrice(230000D);
//
//        ProductDto productDto2 = new ProductDto();
//        productDto2.setId(4L);
//        productDto2.setTitle("MacBook Pro M5 Pro");
//        productDto2.setPrice(280000D);
//
//        List<ProductDto> productDtos = new ArrayList<>();
//        productDtos.add(productDto1);
//        productDtos.add(productDto2);

        String expectedResponse = objectMapper.writeValueAsString(productList);
        // for failing the test purposefully
        // product2.setId(14L);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse)) // response body or content will be converted to json string and will be compared with expectedResponse JSON string
                .andExpect(jsonPath("$[0].title").value("MacBook Pro M3 Pro"));

        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void TestCreateProduct_WithValidRequestBody_RunSuccessfully() throws Exception {
        Product requestProductDto = new Product();
        requestProductDto.setId(10L);
        requestProductDto.setTitle("iPad Air");
        requestProductDto.setPrice(75000D);
        Category category = new Category();
        category.setTitle("Electronics");
        requestProductDto.setCategory(category);

        Product responseProduct = new Product();
        responseProduct.setId(10L);
        responseProduct.setTitle("iPad Air");
        responseProduct.setPrice(75000D);
        Category responseCategory = new Category();
        responseCategory.setTitle("Electronics");
        responseProduct.setCategory(responseCategory);

        // as the productDto obj will be converted to product obj on the fly in productController
        // therefore we cannot pre-determine the type of the obj we need to pass to addProduct() function
        // in this test method
        when(productService.addProduct(any(Product.class))).thenReturn(responseProduct);

        String productDtoString = objectMapper.writeValueAsString(responseProduct);

        mockMvc.perform(post("/products")
                        .content(productDtoString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(productDtoString))
                .andExpect(jsonPath("$.title").value("iPad Air"));
    }

}
