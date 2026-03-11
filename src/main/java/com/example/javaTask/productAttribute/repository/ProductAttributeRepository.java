package com.example.javaTask.productAttribute.repository;

import com.example.javaTask.productAttribute.model.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {}