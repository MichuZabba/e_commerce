package com.example.javaTask.Services;

import com.example.javaTask.Models.Producer;
import com.example.javaTask.Repositories.ProducerRepository;
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
                .orElseThrow(()-> new RuntimeException("Producent does not exists"));

        producerToUpdate.setName(producerUpdateDetails.getName());;

        return producerRepository.save(producerToUpdate);
    }

    public void deleteProducerById(Long id){
        producerRepository.deleteById(id);
    }

    public Producer getProducerById(Long id) {
        return producerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prodecent with id: " + id + " does not exists"));
    }

    public List<Producer> getAllProducers(){
        return producerRepository.findAll();
    }
}
