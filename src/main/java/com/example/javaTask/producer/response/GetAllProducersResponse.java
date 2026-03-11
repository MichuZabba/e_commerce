package com.example.javaTask.producer.response;

import lombok.Data;
import java.util.List;

@Data
public class GetAllProducersResponse {
    private List<ProducerResponse> producers;
}