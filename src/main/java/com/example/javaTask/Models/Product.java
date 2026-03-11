package com.example.javaTask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name of the product is require")
    private String name;

    @Size(max = 1000, message = "Description has max length of 1000 characters")
    private String description;

    @NotNull(message = "Price is require")
    @DecimalMin(value = "0.01",message = "Price must be higher than 0")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    @JsonIgnoreProperties("products")
    private Producer producer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    @Valid
    @JsonManagedReference("product-attribute")
    private List<ProductAttribute> attributes;
}
