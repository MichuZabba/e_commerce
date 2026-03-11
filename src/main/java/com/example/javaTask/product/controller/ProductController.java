package com.example.javaTask.product.controller;

import com.example.javaTask.product.dto.ProductDTO;
import com.example.javaTask.product.response.*;
import com.example.javaTask.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<GetAllProductsResponse> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetProductByIdResponse> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/withAttribute/{attribute}")
    public ResponseEntity<GetProductsByAttributeResponse> getListOFProductsWithGivenAttribute(@PathVariable String attribute) {
        return ResponseEntity.ok(productService.getProductsByAttribute(attribute));
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductResponse> updateProductById(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProductById(id, productDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }
}