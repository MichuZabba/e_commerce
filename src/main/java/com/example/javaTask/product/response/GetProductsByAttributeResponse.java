package com.example.javaTask.product.response;

import lombok.Data;
import java.util.List;

@Data
public class GetProductsByAttributeResponse {
    private List<GetProductByIdResponse> products;
}