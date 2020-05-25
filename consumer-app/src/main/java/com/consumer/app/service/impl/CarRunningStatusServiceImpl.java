package com.consumer.app.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.consumer.app.dto.CarRunningStatusDTO;
import com.consumer.app.model.CarRunningStatus;
import com.consumer.app.repository.CarRunningStatusRepository;
import com.consumer.app.service.CarRunningStatusService;
import com.producer.app.utils.AppUtils;

@Service
public class CarRunningStatusServiceImpl implements CarRunningStatusService {
	@Autowired
	CarRunningStatusRepository carRunningStatusRepository;

	@Override
	@Transactional
	public CarRunningStatusDTO saveStatus(CarRunningStatusDTO carRunningStatusDTO) {

		CarRunningStatus carRunningStatus = AppUtils.getCarStatus(carRunningStatusDTO);

		int variationPercent = displayDataToUser(carRunningStatusDTO);

		carRunningStatus = carRunningStatusRepository.save(carRunningStatus);

		carRunningStatusDTO = AppUtils.getCarStatusDTO(carRunningStatus);

		return variationPercent >= 20 ? carRunningStatusDTO : null;
	}

	public int displayDataToUser(final CarRunningStatusDTO carRunningStatusDTO) {

		Integer randomLat, randomLong;

		Integer randomLat1, randomLong1;

		CarRunningStatus lastSecondData = carRunningStatusRepository.findLastSecodRecord();

		CarRunningStatusDTO existingStatus = lastSecondData != null ? AppUtils.getCarStatusDTO(lastSecondData) : null;

		int vPer = 0;

		if (existingStatus != null && carRunningStatusDTO != null) {

			randomLat = carRunningStatusDTO.getLatlong().get(0);
			randomLong = carRunningStatusDTO.getLatlong().get(1);
			randomLat1 = existingStatus.getLatlong().get(0);
			randomLong1 = existingStatus.getLatlong().get(1);
			if (randomLat > randomLat1) {

				vPer = (randomLat - randomLat1) * 100 / randomLat;

			} else if (randomLat < randomLat1) {

				vPer = (randomLat1 - randomLat) * 100 / randomLat1;
			}

			if (vPer > 20) {

				return vPer;
			}
		}
		return vPer;

	}
}
