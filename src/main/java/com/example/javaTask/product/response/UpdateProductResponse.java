package com.example.javaTask.product.response;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class UpdateProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private ProducerInProductResponse producer;
    private List<AttributeInProductResponse> attributes;
}