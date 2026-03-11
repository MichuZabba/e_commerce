package com.example.javaTask.producer.repository;

import com.example.javaTask.producer.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {}