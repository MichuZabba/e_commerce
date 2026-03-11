package com.example.javaTask.product.response;

import lombok.Data;

import java.util.List;

@Data
public class PagedResponse<T> {
    private List<T> result;
    private int currentPage;
    private int numberOfPages;
}
