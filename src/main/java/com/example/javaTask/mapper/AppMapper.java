package com.example.javaTask.mapper;

import com.example.javaTask.producer.dto.ProducerDTO;
import com.example.javaTask.producer.model.Producer;
import com.example.javaTask.producer.response.*;
import com.example.javaTask.product.dto.ProductDTO;
import com.example.javaTask.product.model.Product;
import com.example.javaTask.product.response.*;
import com.example.javaTask.productAttribute.dto.ProductAttributeDTO;
import com.example.javaTask.productAttribute.model.ProductAttribute;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AppMapper {

    // Producer
    ProducerDTO toDTO(Producer producer);
    Producer toEntity(ProducerDTO dto);
    ProducerResponse toGetProducerByIdResponse(Producer producer);
    ProducerResponse toCreateProducerResponse(Producer producer);
    ProducerResponse toUpdateProducerResponse(Producer producer);
    ProducerResponse toProducerResponse(Producer producer);

    // Product
    ProductDTO toDTO(Product product);

    @Mapping(target = "attributes", ignore = true)
    Product toEntity(ProductDTO dto);

    GetProductByIdResponse toGetProductByIdResponse(Product product);
    CreateProductResponse toCreateProductResponse(Product product);
    UpdateProductResponse toUpdateProductResponse(Product product);

    // ProductAttribute
    ProductAttributeDTO toDTO(ProductAttribute attr);

    @Mapping(target = "product", ignore = true)
    ProductAttribute toEntity(ProductAttributeDTO dto);
}