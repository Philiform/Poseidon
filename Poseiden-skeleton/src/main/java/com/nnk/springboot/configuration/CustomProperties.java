package com.nnk.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix="com.nnk.springboot")
public class CustomProperties {

	private String apiUrl;
	
}
