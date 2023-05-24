package com.sereda.ecommerce_project.service;

import com.sereda.ecommerce_project.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(Long productId);

    List<ProductDto> getProducts();

    void deleteProduct(Long productId);

    ProductDto updateProduct(Long productId, ProductDto productWithChanges);
}
