package com.example.java_task.Models;

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

    @NotBlank(message = "Nazwa produktu jest wymagana")
    private String name;

    @Size(max = 1000, message = "Opis moze miec maksymalnie 1000 znakow")
    private String description;

    @NotNull(message = "Cena jest wymagana")
    @DecimalMin(value = "0.01",message = "Cena musi być wieksza od 0")
    private BigDecimal price;

    @NotNull(message = "Produkt musi mieć przypisanego producenta")
    @ManyToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL,orphanRemoval = true)
    @Valid
    private List<ProductAttribute> attributes;
}
