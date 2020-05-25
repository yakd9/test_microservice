package com.producer.app.service;

import java.util.List;

import com.producer.app.dto.CarRunningStatusDTO;



public interface CarRunningStatusService {
	
	public void sendCarRunningInfo(List<CarRunningStatusDTO> carRunningStatusDTOList);

}
