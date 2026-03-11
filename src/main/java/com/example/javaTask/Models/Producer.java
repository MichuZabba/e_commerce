package com.example.javaTask.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @NotNull(message = "Producent name is requier")
    private String name;

    @OneToMany(mappedBy = "producer", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("producer")
    private List<Product> products;

}
