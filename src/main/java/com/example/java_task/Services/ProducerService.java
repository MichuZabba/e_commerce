package com.example.java_task.Services;

import com.example.java_task.Models.Producer;
import com.example.java_task.Models.Product;
import com.example.java_task.Repositories.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProducerService {

    private final ProducerRepository producerRepository;

    public Producer createProducer(Producer producer){
        return producerRepository.save(producer);
    }

    @Transactional
    public Producer updateProducerById(Long id,Producer producerUpdateDetails){
        Producer producerToUpdate = producerRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Produkt nie istnieje"));

        producerToUpdate.setName(producerUpdateDetails.getName());;

        return producerRepository.save(producerToUpdate);
    }

    public void deleteProducerById(Long id){
        producerRepository.deleteById(id);
    }

    public Producer getProducerById(Long id) {
        return producerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producent o danym: " + id + " nie istnieje"));
    }

    public List<Producer> getAllProducers(){
        return producerRepository.findAll();
    }
}
