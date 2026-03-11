package com.example.javaTask.producer.controller;

import com.example.javaTask.producer.dto.ProducerDTO;
import com.example.javaTask.producer.response.*;
import com.example.javaTask.producer.service.ProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<GetAllProducersResponse> getAllProducers() {
        return ResponseEntity.ok(producerService.getAllProducers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProducerResponse> getProducerById(@PathVariable Long id) {
        return ResponseEntity.ok(producerService.getProducerById(id));
    }

    @PostMapping
    public ResponseEntity<ProducerResponse> createProducer(@Valid @RequestBody ProducerDTO producerDTO) {
        return new ResponseEntity<>(producerService.createProducer(producerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProducerResponse> updateProducerById(@PathVariable Long id, @Valid @RequestBody ProducerDTO producerDTO) {
        return ResponseEntity.ok(producerService.updateProducerById(id, producerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducerById(@PathVariable Long id) {
        producerService.deleteProducerById(id);
        return ResponseEntity.noContent().build();
    }
}