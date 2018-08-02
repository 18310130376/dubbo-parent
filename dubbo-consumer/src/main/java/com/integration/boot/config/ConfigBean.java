package com.integration.boot.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "test")
public class ConfigBean {  
	
	private List<String> abc;

	public List<String> getAbc() {
		return abc;
	}

	public void setAbc(List<String> abc) {
		this.abc = abc;
	}

}
