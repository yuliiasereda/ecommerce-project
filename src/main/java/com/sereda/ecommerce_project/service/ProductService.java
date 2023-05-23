package com.sereda.ecommerce_project.service;

import com.sereda.ecommerce_project.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    Product createProduct(Product product);
    Optional<Product> getProduct(Long productId);
    List<Product> getProducts();
    boolean deleteProduct(Long productId);
    Product updateProduct(Product product);
}
