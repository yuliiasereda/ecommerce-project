package com.sereda.ecommerce_project.controller;

import com.sereda.ecommerce_project.dto.ProductDto;
import com.sereda.ecommerce_project.model.Product;
import com.sereda.ecommerce_project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        productDto.setCreatedAt(LocalDateTime.now());
        Product product = convertDtoToEntity(productDto);
        Product createdProduct = productService.createProduct(product);
        ProductDto createdProductDto = convertEntityToDto(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
    }

    private Product convertDtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        return product;
    }

    private ProductDto convertEntityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand());
        productDto.setPrice(product.getPrice());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        return productDto;
    }
}
