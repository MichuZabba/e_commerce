package com.example.java_task.Services;

import com.example.java_task.Models.Producer;
import com.example.java_task.Repositories.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {
    private final ProducerRepository producerRepository;

    public Producer createProducer(Producer producer){
        return producerRepository.save(producer);
    }

    public void deleteProducerById(Long id){
        producerRepository.deleteById(id);
    }

    public Producer getProducerById(Long id) {
        return producerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producer not found with id: " + id));
    }

    public List<Producer> getAllProducers(){
        return producerRepository.findAll();
    }
}
