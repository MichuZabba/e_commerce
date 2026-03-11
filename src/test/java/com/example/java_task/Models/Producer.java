package com.example.java_task.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Producer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Nazwa producenta jest wymagana")
    private String name;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    private List<Product> products;

}
