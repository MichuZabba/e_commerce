package com.example.javaTask.Services;

import com.example.javaTask.Models.Producer;
import com.example.javaTask.Models.Product;
import com.example.javaTask.Models.ProductAttribute;
import com.example.javaTask.Repositories.ProducerRepository;
import com.example.javaTask.Repositories.ProductRepository;
import com.example.javaTask.Specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        else {
            throw new RuntimeException("Nie mozna utworzyc produktu bez producenta");
        }

        if(product.getAttributes() != null)
        {
            product.getAttributes().forEach(attribute->attribute.setProduct((product)));
        }

        return productRepository.save(product);
    }

    @Transactional
    public Product updateProductById(Long id, Product productToUpdateDetails){
        Product productToUpdate = productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product does not exists"));

        productToUpdate.setName(productToUpdateDetails.getName());
        productToUpdate.setDescription(productToUpdateDetails.getDescription());
        productToUpdate.setPrice(productToUpdateDetails.getPrice());

        if (productToUpdateDetails.getAttributes() != null) {
            Map<String, ProductAttribute> existingAttributes = productToUpdate.getAttributes().stream()
                    .collect(Collectors.toMap(ProductAttribute::getKey, attr -> attr));

            productToUpdateDetails.getAttributes().forEach(newAttr -> {
                if (existingAttributes.containsKey(newAttr.getKey())) {
                    ProductAttribute attrToUpdate = existingAttributes.get(newAttr.getKey());
                    attrToUpdate.setValue(newAttr.getValue());
                } else {
                    newAttr.setProduct(productToUpdate);
                    productToUpdate.getAttributes().add(newAttr);
                }
            });
        }
        return productRepository.save(productToUpdate);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Products with id " + id + " does not exists"));
    }

    public List<Product> getProductsByAttribute(String key) {
        return productRepository.findAll(ProductSpecifications.hasAttribute(key));
    }
}
