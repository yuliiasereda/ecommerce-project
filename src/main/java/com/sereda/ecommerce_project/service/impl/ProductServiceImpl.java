package com.sereda.ecommerce_project.service.impl;

import com.sereda.ecommerce_project.converter.ProductConverter;
import com.sereda.ecommerce_project.dto.ProductDto;
import com.sereda.ecommerce_project.exception.RecordNotFoundException;
import com.sereda.ecommerce_project.model.Product;
import com.sereda.ecommerce_project.repository.ProductRepository;
import com.sereda.ecommerce_project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.sereda.ecommerce_project.converter.ProductConverter.convertDtoToEntity;
import static com.sereda.ecommerce_project.converter.ProductConverter.convertEntityToDto;

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
                .map(ProductConverter::convertEntityToDto)
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
}
