package com.producer.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.producer.app.dto.CarRunningStatusDTO;

@Service
public class Sender {


    @Autowired
    private KafkaTemplate<String, CarRunningStatusDTO> kafkaTemplate;

    public void send(CarRunningStatusDTO carRunningStatusDTO) {
        kafkaTemplate.send("CAR_RUNNING_STATUS", carRunningStatusDTO);
    }
}