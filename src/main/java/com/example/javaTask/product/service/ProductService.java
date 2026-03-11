package com.example.javaTask.product.service;

import com.example.javaTask.common.search.PagedResponse;
import com.example.javaTask.exception.ResourceNotFoundException;
import com.example.javaTask.mapper.AppMapper;
import com.example.javaTask.producer.model.Producer;
import com.example.javaTask.producer.repository.ProducerRepository;
import com.example.javaTask.product.dto.ProductDTO;
import com.example.javaTask.product.model.Product;
import com.example.javaTask.product.repository.ProductRepository;
import com.example.javaTask.product.request.SearchProductRequest;
import com.example.javaTask.product.response.*;
import com.example.javaTask.productAttribute.model.ProductAttribute;
import com.example.javaTask.Specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
        List<GetProductResponse> list = productRepository.findAll().stream()
                .map(appMapper::toGetProductByIdResponse)
                .collect(Collectors.toList());
        GetAllProductsResponse response = new GetAllProductsResponse();
        response.setProducts(list);
        return response;
    }

    public GetProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product does not exists"));
        return appMapper.toGetProductByIdResponse(product);
    }

    public GetProductsByAttributeResponse getProductsByAttribute(String key) {
        List<GetProductResponse> list = productRepository
                .findAll(ProductSpecifications.hasAttribute(key)).stream()
                .map(appMapper::toGetProductByIdResponse)
                .collect(Collectors.toList());
        GetProductsByAttributeResponse response = new GetProductsByAttributeResponse();
        response.setProducts(list);
        return response;
    }

    public PagedResponse<GetProductResponse> searchProduct(SearchProductRequest searchProductRequest)
    {
        Sort.Direction direction = Sort.Direction.DESC;
        if(searchProductRequest.getSortDirection() !=null && searchProductRequest.getSortDirection().equalsIgnoreCase("ASC")){
            direction = Sort.Direction.ASC;
        }

        String sortBy = (searchProductRequest.getSortBy() != null && !searchProductRequest.getSortBy().isEmpty())
                ? searchProductRequest.getSortBy()
                : "id";

        Pageable pageable = PageRequest.of(
                searchProductRequest.getPageNumber(),
                searchProductRequest.getPageSize(),
                Sort.by(direction,sortBy)
        );

        Specification<Product> spec = (root, query, cb) -> {
            String filter = searchProductRequest.getFilter();
            String filterBy = searchProductRequest.getFilterBy();
            String operand = searchProductRequest.getFilterOperand();

            if (filter == null || filterBy == null || operand == null) {
                return null;
            }

            return switch (operand.toLowerCase()) {
                case "like" -> cb.like(cb.lower(root.get(filterBy)), "%" + filter.toLowerCase() + "%");
                case "eq"   -> cb.equal(root.get(filterBy), filter);
                case "gt"   -> cb.greaterThan(root.get(filterBy), filter);
                case "lt"   -> cb.lessThan(root.get(filterBy), filter);
                default     -> null;
            };
        };

        Page<Product> productPage = productRepository.findAll(spec,pageable);

        List<GetProductResponse> mappedResults = productPage.getContent().stream()
                .map(appMapper::toGetProductResponse)
                .toList();

        PagedResponse<GetProductResponse> response = new PagedResponse<>();
        response.setResult(mappedResults);
        response.setCurrentPage(searchProductRequest.getPageNumber());
        response.setNumberOfPages(productPage.getTotalPages());

        return response;
    }
}