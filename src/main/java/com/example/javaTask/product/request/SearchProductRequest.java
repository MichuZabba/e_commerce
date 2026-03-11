package com.example.javaTask.product.request;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

@Data
public class SearchProductRequest {

    @Min(value = 0, message = "Page number must be 0 or greater")
    private int pageNumber;

    @Min(value = 1, message = "Page size must be at least 1")
    private int pageSize;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Sort field must contain only letters")
    private String sortBy;

    @Pattern(regexp = "(?i)^(ASC|DESC)$", message = "Sort direction must be ASC or DESC")
    private String sortDirection;

    private String filter;

    @Pattern(regexp = "^(name|description|price)$", message = "Filter field must be: name, description, or price")
    private String filterBy;

    @Pattern(regexp = "^(eq|gt|lt|like)$", message = "Operand must be one of: eq, gt, lt, like")
    private String filterOperand;
}
