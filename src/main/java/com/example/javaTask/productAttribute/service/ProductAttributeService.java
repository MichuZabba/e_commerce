package com.example.javaTask.productAttribute.service;

import com.example.javaTask.product.repository.ProductRepository;
import com.example.javaTask.productAttribute.repository.ProductAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;

    @Autowired
    public  ProductAttributeService(ProductAttributeRepository productAttributeRepository){
        this.productAttributeRepository = productAttributeRepository;
    }

    public void deleteAttributeById(Long id){
        productAttributeRepository.deleteById(id);
    }
}
