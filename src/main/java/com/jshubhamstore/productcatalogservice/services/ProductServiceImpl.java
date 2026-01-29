package com.jshubhamstore.productcatalogservice.services;

import com.jshubhamstore.productcatalogservice.exceptions.ProductNotFoundException;
import com.jshubhamstore.productcatalogservice.models.Category;
import com.jshubhamstore.productcatalogservice.models.Product;
import com.jshubhamstore.productcatalogservice.repositories.CategoryRepository;
import com.jshubhamstore.productcatalogservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceImpl implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        return optionalProduct.get();
    }

//    @Override
//    public Product addProduct(Product input) {
//        return null;
//    }
  @Override
    public Product addProduct(Product product) {
        // Add Validations based on business requirements
        // TODO Add more validations possible and add corresponding exceptions
        if(product.getId()!=null) {
            Optional<Product> optionalProduct = productRepository.findById(product.getId());
            if(optionalProduct.isEmpty()) throw new RuntimeException("Invalid Product");
        }
        if(product.getCategory()!=null) {
            Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());
            if(optionalCategory.isEmpty()) {
                Category newCategory = new Category();
                newCategory.setTitle(product.getCategory().getTitle());
                Category savedCategory = categoryRepository.save(newCategory);
                product.setCategory(savedCategory);
            }
        }else {
            throw new RuntimeException("Cannot add Product without a Category");
        }

        return productRepository.save(product);
    }
//
//    @Override
//    public Product updateProduct(Long productId, Product product) {
//        return null;
//    }
//
//    @Override
//    public Boolean removeProduct(Long productId) {
//        productRepository.deleteById(productId);
//        return true;
//    }
}
