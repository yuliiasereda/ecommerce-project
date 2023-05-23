package com.sereda.ecommerce_project.service;

import com.sereda.ecommerce_project.model.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public Product createProduct(Product product);
}
