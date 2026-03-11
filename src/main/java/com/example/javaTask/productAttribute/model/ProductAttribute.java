package com.example.javaTask.productAttribute.model;

import com.example.javaTask.product.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Key can't be empty")
    @Size(max = 200,message = "Key is too long max length is 200 characters")
    @Column(name = "attr_key")
    private String key;

    @NotBlank(message = "Value can't be empty")
    @Size(max = 100,message = "Value is too long max length is 100 characters" )
    @Column(name = "attr_value")
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("product-attribute")
    private Product product;
}
