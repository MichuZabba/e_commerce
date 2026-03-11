package com.example.javaTask.Services;

import com.example.javaTask.Repositories.ProductAttributeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAttributeService {

    private final ProductAttributeRepository productAttributeRepository;

    public void deleteAttributeById(Long id){
        productAttributeRepository.deleteById(id);
    }
}
