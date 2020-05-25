package com.consumer.app.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.consumer.app.dto.CarRunningStatusDTO;



@Configuration
@EnableKafka
public class KafkaConsumerConfig {
	
	public KafkaConsumerConfig(){}
	
	
	@Bean
	public ConsumerFactory<String, CarRunningStatusDTO> consumerFactory(){
		JsonDeserializer<CarRunningStatusDTO> deserializer=new JsonDeserializer<>(CarRunningStatusDTO.class);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		return new DefaultKafkaConsumerFactory<>(consumerConfig(),new StringDeserializer(),deserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CarRunningStatusDTO> kafkaListenerContainerFactory(){
	ConcurrentKafkaListenerContainerFactory<String, CarRunningStatusDTO> factory=new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;		
	}
	
	@Bean
	public Map<String,Object> consumerConfig(){
		JsonDeserializer<CarRunningStatusDTO> deserializer=new JsonDeserializer<>(CarRunningStatusDTO.class);
		deserializer.addTrustedPackages("*");
		deserializer.setUseTypeMapperForKey(true);
		Map<String,Object> propMap=new HashMap<>();
		propMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		propMap.put(ConsumerConfig.GROUP_ID_CONFIG, "boot");
		propMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		propMap.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "123456");
		propMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propMap.put(ConsumerConfig.GROUP_ID_CONFIG, "json");
		propMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);
		propMap.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 2000);		
		return propMap;
		
		
		
	}
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
