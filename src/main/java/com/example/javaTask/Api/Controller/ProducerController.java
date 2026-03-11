package com.example.javaTask.Api.Controller;


import com.example.javaTask.Models.Producer;
import com.example.javaTask.Services.ProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<List<Producer>> getAllProducers(){
        List<Producer> producers = producerService.getAllProducers();
        return ResponseEntity.ok(producers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producer> getProducerById(@PathVariable Long id){
        return ResponseEntity.ok(producerService.getProducerById(id));
    }

    @PostMapping
    public ResponseEntity<Producer> createProducer(@Valid @RequestBody Producer producer){
        Producer created = producerService.createProducer(producer);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producer> updateProducerById(@PathVariable Long id, @Valid @RequestBody Producer producer){
        return ResponseEntity.ok(producerService.updateProducerById(id,producer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducerById(@PathVariable Long id){
        producerService.deleteProducerById(id);
        return ResponseEntity.noContent().build();
    }
}
