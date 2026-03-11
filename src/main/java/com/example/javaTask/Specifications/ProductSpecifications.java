package com.example.javaTask.Specifications;

import com.example.javaTask.Models.Product;
import com.example.javaTask.Models.ProductAttribute;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> hasAttribute(String key){
        return (root, query, criteriaBuilder) -> {
            if(key == null) return null;

            Join<Product, ProductAttribute> attributeJoin = root.join("attributes");

            return criteriaBuilder.and(
                    criteriaBuilder.equal(attributeJoin.get("key"),key)
            );
        };
    }
}
