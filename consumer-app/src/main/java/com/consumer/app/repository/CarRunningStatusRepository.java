package com.consumer.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.consumer.app.model.CarRunningStatus;

@Repository
public interface CarRunningStatusRepository  extends JpaRepository<CarRunningStatus, Long>{
	
	
	@Query(value = "SELECT *FROM car_running_status  ORDER BY timestamp DESC LIMIT 0,1",nativeQuery = true)
	public CarRunningStatus findLastSecodRecord();

}
