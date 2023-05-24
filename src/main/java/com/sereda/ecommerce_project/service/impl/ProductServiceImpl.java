package com.sereda.ecommerce_project.service.impl;

import com.sereda.ecommerce_project.dto.ProductDto;
import com.sereda.ecommerce_project.exception.RecordNotFoundException;
import com.sereda.ecommerce_project.model.Product;
import com.sereda.ecommerce_project.repository.ProductRepository;
import com.sereda.ecommerce_project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        productDto.setCreatedAt(LocalDateTime.now());
        Product product = convertDtoToEntity(productDto);
        Product createdProduct = productRepository.save(product);
        return convertEntityToDto(createdProduct);
    }

    @Override
    public ProductDto getProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException(Product.class, String.valueOf(productId)));
        return convertEntityToDto(product);
    }

    @Override
    public List<ProductDto> getProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException(Product.class, String.valueOf(productId)));
        product.setAvailability(false);
        productRepository.save(product);
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto productWithChanges) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RecordNotFoundException(Product.class, String.valueOf(productId)));
        product.setName(productWithChanges.getName());
        product.setDescription(productWithChanges.getDescription());
        product.setBrand(productWithChanges.getBrand());
        product.setPrice(productWithChanges.getPrice());

        Product updatedProduct = productRepository.save(product);
        return convertEntityToDto(updatedProduct);
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
        productDto.setAvailability(product.getAvailability());
        return productDto;
    }

    private Product convertDtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setBrand(productDto.getBrand());
        product.setPrice(productDto.getPrice());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setAvailability(productDto.getAvailability());
        return product;
    }
}
