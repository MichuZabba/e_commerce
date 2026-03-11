package com.example.javaTask.product.dto;

import com.example.javaTask.producer.dto.ProducerDTO;
import com.example.javaTask.producer.model.Producer;
import com.example.javaTask.productAttribute.dto.ProductAttributeDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProducerDTO producer;
    private List<ProductAttributeDTO> attributes;
}
