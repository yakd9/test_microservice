package com.consumer.app.service;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.consumer.app.dto.CarRunningStatusDTO;


@Component
public class ReceiverService {
	private static final Logger log = LoggerFactory.getLogger(ReceiverService.class);
	
	

@Autowired
CarRunningStatusService carRunningStatusService;
	private CountDownLatch latch = new CountDownLatch(1);

	@KafkaListener(topics = "CAR_RUNNING_STATUS")

	public void receive(CarRunningStatusDTO carRunningStatusDTO) {

		carRunningStatusDTO=carRunningStatusService.saveStatus(carRunningStatusDTO);
		
		log.info(carRunningStatusDTO!=null?carRunningStatusDTO.toString():"There is no variation");
		
		latch.countDown();
	}
	
	
	

}
