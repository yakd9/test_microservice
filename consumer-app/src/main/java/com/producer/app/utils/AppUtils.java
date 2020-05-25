package com.producer.app.utils;

import java.util.Arrays;
import java.util.List;

import com.consumer.app.dto.CarRunningStatusDTO;
import com.consumer.app.model.CarRunningStatus;


public class AppUtils {


	public static CarRunningStatus getCarStatus(CarRunningStatusDTO carRunningStatusDTO) {

		CarRunningStatus carRunningStatus = new CarRunningStatus();
		carRunningStatus
				.setLatlong(carRunningStatusDTO.getLatlong().get(0) + "," + carRunningStatusDTO.getLatlong().get(1));
		// CarRunningStatus carRunningStatus=modelMapper.map(carRunningStatusDTO,
		// CarRunningStatus.class);
		carRunningStatus.setTimestamp(carRunningStatusDTO.getTimestamp());
		return carRunningStatus;
	}

	public static CarRunningStatusDTO getCarStatusDTO(CarRunningStatus carRunningStatus) {
		CarRunningStatusDTO carRunningStatusDTO = new CarRunningStatusDTO();
		carRunningStatusDTO.setId(carRunningStatus.getId());
		carRunningStatusDTO.setLatlong(getIntegerValue(carRunningStatus.getLatlong()));
		carRunningStatusDTO.setTimestamp(carRunningStatus.getTimestamp());
		return carRunningStatusDTO;
	}
	
	public static List<Integer> getIntegerValue(String latlong){
		
		try {
		String[] str= latlong.split(",");	
		return Arrays.asList(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
		}
		catch(Exception e) {
			
		}
			return null;
		}
		
		
	
}
