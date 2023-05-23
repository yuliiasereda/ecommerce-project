package com.sereda.ecommerce_project.service.impl;

import com.sereda.ecommerce_project.model.Product;
import com.sereda.ecommerce_project.repository.ProductRepository;
import com.sereda.ecommerce_project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
