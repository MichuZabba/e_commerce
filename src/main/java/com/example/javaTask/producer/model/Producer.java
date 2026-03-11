package com.example.javaTask.producer.model;

import com.example.javaTask.product.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Producer name is requier")
    private String name;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("producer")
    private List<Product> products;

}
