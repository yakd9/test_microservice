package com.producer.app.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.producer.app.dto.CarRunningStatusDTO;
import com.producer.app.service.CarRunningStatusService;
import com.producer.app.service.Sender;

@Service
public class CarRunningStatusServiceImpl implements CarRunningStatusService {

	//@Autowired
	//private ModelMapper modelMapper;

	@Autowired
	private Sender sender;

	@Override
	@Transactional
	public void sendCarRunningInfo(List<CarRunningStatusDTO> carRunningStatusDTOList) {

		carRunningStatusDTOList.forEach(carRunningStatusDTO -> sender.send(carRunningStatusDTO));

	}
}
