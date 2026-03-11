package com.example.java_task.Services;

import com.example.java_task.Models.Producer;
import com.example.java_task.Models.Product;
import com.example.java_task.Repositories.ProducerRepository;
import com.example.java_task.Repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;

    @Transactional
    public Product createProduct(Product product){

        if(product.getProducer() != null && product.getProducer().getId() !=null)
        {
            Producer producer = producerRepository.findById(product.getProducer().getId())
                    .orElseThrow(() -> new RuntimeException("Producer not found"));
            product.setProducer(producer);
        }

        if(product.getAttributes() != null)
        {
            product.getAttributes().forEach(attribute->attribute.setProduct((product)));
        }

        return productRepository.save(product);
    }

    public void removeProductById(Long id){
        productRepository.deleteById(id);
    }
}
