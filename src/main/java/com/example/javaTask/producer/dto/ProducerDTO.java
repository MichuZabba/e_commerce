package com.example.javaTask.producer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProducerDTO {

    @NotNull(message = "Producer ID cannot be null")
    private Long id;

    @NotBlank(message = "Producer name cannot be empty")
    @Size(min = 2, max = 50, message = "Producer name must be between 2 and 50 characters")
    private String name;
}