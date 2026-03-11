package com.example.javaTask.product.service;

import com.example.javaTask.exception.ResourceNotFoundException;
import com.example.javaTask.mapper.AppMapper;
import com.example.javaTask.producer.model.Producer;
import com.example.javaTask.producer.repository.ProducerRepository;
import com.example.javaTask.product.dto.ProductDTO;
import com.example.javaTask.product.model.Product;
import com.example.javaTask.product.repository.ProductRepository;
import com.example.javaTask.product.response.*;
import com.example.javaTask.productAttribute.model.ProductAttribute;
import com.example.javaTask.Specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;
    private final AppMapper appMapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProducerRepository producerRepository, AppMapper appMapper)
    {
        this.producerRepository = producerRepository;
        this.productRepository = productRepository;
        this.appMapper = appMapper;
    }

    @Transactional
    public CreateProductResponse createProduct(ProductDTO productDTO) {
        if (productDTO.getProducer() == null || productDTO.getProducer().getId() == null) {
            throw new RuntimeException("Nie mozna utworzyc produktu bez producenta");
        }
        Producer producer = producerRepository.findById(productDTO.getProducer().getId())
                .orElseThrow(() -> new RuntimeException("Producer not found"));

        Product product = appMapper.toEntity(productDTO);
        product.setProducer(producer);

        if (productDTO.getAttributes() != null) {
            List<ProductAttribute> attributes = productDTO.getAttributes().stream()
                    .map(attrDTO -> {
                        ProductAttribute attr = appMapper.toEntity(attrDTO);
                        attr.setProduct(product);
                        return attr;
                    })
                    .collect(Collectors.toList());
            product.setAttributes(attributes);
        }

        return appMapper.toCreateProductResponse(productRepository.save(product));
    }

    @Transactional
    public UpdateProductResponse updateProductById(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exists"));

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());

        if (productDTO.getAttributes() != null) {
            Map<String, ProductAttribute> existingAttributes = product.getAttributes().stream()
                    .collect(Collectors.toMap(ProductAttribute::getKey, attr -> attr));

            productDTO.getAttributes().forEach(newAttrDTO -> {
                if (existingAttributes.containsKey(newAttrDTO.getKey())) {
                    existingAttributes.get(newAttrDTO.getKey()).setValue(newAttrDTO.getValue());
                } else {
                    ProductAttribute attr = appMapper.toEntity(newAttrDTO);
                    attr.setProduct(product);
                    product.getAttributes().add(attr);
                }
            });
        }

        return appMapper.toUpdateProductResponse(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    public GetAllProductsResponse getAllProducts() {
        List<GetProductByIdResponse> list = productRepository.findAll().stream()
                .map(appMapper::toGetProductByIdResponse)
                .collect(Collectors.toList());
        GetAllProductsResponse response = new GetAllProductsResponse();
        response.setProducts(list);
        return response;
    }

    public GetProductByIdResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exists"));
        return appMapper.toGetProductByIdResponse(product);
    }

    public GetProductsByAttributeResponse getProductsByAttribute(String key) {
        List<GetProductByIdResponse> list = productRepository
                .findAll(ProductSpecifications.hasAttribute(key)).stream()
                .map(appMapper::toGetProductByIdResponse)
                .collect(Collectors.toList());
        GetProductsByAttributeResponse response = new GetProductsByAttributeResponse();
        response.setProducts(list);
        return response;
    }
}