package com.sereda.ecommerce_project.controller;

import com.sereda.ecommerce_project.dto.ProductDto;
import com.sereda.ecommerce_project.model.Product;
import com.sereda.ecommerce_project.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<Product> productList = productService.getProducts();
        List<ProductDto> productDtoList = productList
                .stream()
                .map(this::convertEntityToDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        productDto.setCreatedAt(LocalDateTime.now());
        Product product = convertDtoToEntity(productDto);
        Product createdProduct = productService.createProduct(product);
        ProductDto createdProductDto = convertEntityToDto(createdProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProductDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId){
        Optional<Product> optionalProduct = productService.getProduct(productId);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            ProductDto productDto = convertEntityToDto(product);
            return ResponseEntity.status(HttpStatus.OK).body(productDto);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping({"/{productId}"})
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long productId){
        boolean isDeleted = productService.deleteProduct(productId);
        if(isDeleted)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping({"/{productId}"})
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductDto productWithChanges){
        Optional<Product> optionalProduct = productService.getProduct(productId);

        if(optionalProduct.isPresent()){
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(productWithChanges.getName());
            existingProduct.setDescription(productWithChanges.getDescription());
            existingProduct.setBrand(productWithChanges.getBrand());
            existingProduct.setPrice(productWithChanges.getPrice());

            Product updatedProduct = productService.updateProduct(existingProduct);
            ProductDto updatedProductDto = convertEntityToDto(updatedProduct);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedProductDto);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
        productDto.setProductId(product.getProductId()
        );
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand());
        productDto.setPrice(product.getPrice());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        return productDto;
    }
}
