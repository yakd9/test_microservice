package com.producer.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.producer.app.dto.CarRunningStatusDTO;
import com.producer.app.model.ApiResponse;
import com.producer.app.service.CarRunningStatusService;
import com.producer.app.utils.Constants;

@RestController
@RequestMapping("/car")
public class CarRunningStatusController {
	
	 private static final Logger log = LoggerFactory.getLogger(CarRunningStatusController.class);
	
	@Autowired
	private CarRunningStatusService carService;

	@PostMapping(value = { "/current_status" })
	public ApiResponse sendCarRunningInfo(@RequestBody List<CarRunningStatusDTO> carRunningStatusDTO){
		log.info("received request to list car info) ");
		String sentStatus;
		try {
		carService.sendCarRunningInfo(carRunningStatusDTO);		
		}catch(Exception e){
			return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage() );
		}		
		return new ApiResponse(HttpStatus.OK, Constants.SUCCESS);
		
	
	}
}
