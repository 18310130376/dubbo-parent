package com.integration.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StaticProperties {

	public static String SPRING_APPLICATION_NAME;
	
	@SuppressWarnings("static-access")
	@Value("${spring.dubbo.application.name}")
	private void setApplicationName(String applicationName){
		this.SPRING_APPLICATION_NAME = applicationName;
	}
	
	public static void main(String[] args) {
		
		System.out.println(StaticProperties.SPRING_APPLICATION_NAME);
	}
	
}
