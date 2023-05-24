package com.sereda.ecommerce_project.converter;

import com.sereda.ecommerce_project.dto.ProductDto;
import com.sereda.ecommerce_project.model.Product;

public class ProductConverter {
    public static ProductDto convertEntityToDto(Product product){
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

    public static Product convertDtoToEntity(ProductDto productDto){
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
