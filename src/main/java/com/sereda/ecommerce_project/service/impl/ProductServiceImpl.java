package com.sereda.ecommerce_project.service.impl;

import com.sereda.ecommerce_project.model.Product;
import com.sereda.ecommerce_project.repository.ProductRepository;
import com.sereda.ecommerce_project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public boolean deleteProduct(Long productId) {
        if(productRepository.existsById(productId)) {
            productRepository.deleteById(productId);
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }
}
