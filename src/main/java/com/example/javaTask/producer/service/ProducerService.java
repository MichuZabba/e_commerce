package com.example.javaTask.producer.service;

import com.example.javaTask.exception.ResourceNotFoundException;
import com.example.javaTask.mapper.AppMapper;
import com.example.javaTask.producer.dto.ProducerDTO;
import com.example.javaTask.producer.model.Producer;
import com.example.javaTask.producer.repository.ProducerRepository;
import com.example.javaTask.producer.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final AppMapper appMapper;

    @Autowired
    public ProducerService(ProducerRepository producerRepository, AppMapper appMapper)
    {
        this.producerRepository = producerRepository;
        this.appMapper = appMapper;
    }
    public ProducerResponse createProducer(ProducerDTO producerDTO) {
        Producer producer = appMapper.toEntity(producerDTO);
        return appMapper.toCreateProducerResponse(producerRepository.save(producer));
    }

    public ProducerResponse updateProducerById(Long id, ProducerDTO producerDTO) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producer does not exists"));
        producer.setName(producerDTO.getName());
        return appMapper.toUpdateProducerResponse(producerRepository.save(producer));
    }

    public void deleteProducerById(Long id) {
        producerRepository.deleteById(id);
    }

    public ProducerResponse getProducerById(Long id) {
        Producer producer = producerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producer with id: " + id + " does not exists"));
        return appMapper.toGetProducerByIdResponse(producer);
    }

    public GetAllProducersResponse getAllProducers() {
        List<ProducerResponse> list = producerRepository.findAll().stream()
                .map(appMapper::toProducerResponse)
                .collect(Collectors.toList());
        GetAllProducersResponse response = new GetAllProducersResponse();
        response.setProducers(list);
        return response;
    }
}