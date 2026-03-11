package com.example.java_task.Services;

import com.example.java_task.Repositories.ProductAttributeRepository;
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
