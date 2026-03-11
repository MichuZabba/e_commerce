package com.example.java_task.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Key musi nie moze byc pusty")
    @Size(max = 200,message = "Key jest za dlugi")
    private String key;
    @NotBlank(message = "Value musi nie moze byc pusty")
    @Size(max = 100,message = "Value jest za dlugi")
    private String value;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
