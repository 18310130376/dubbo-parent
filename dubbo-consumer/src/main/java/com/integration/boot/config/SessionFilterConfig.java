package com.integration.boot.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionFilterConfig {
	
	   @Bean
	    public FilterRegistrationBean testFilterRegistration() {
	        StringBuffer excludedUriStr = new StringBuffer();
	        excludedUriStr.append("/login/*");
	        FilterRegistrationBean registration = new FilterRegistrationBean();
	        registration.setFilter(new SessionFilter());
	        registration.addUrlPatterns("/*");
	        registration.addInitParameter("excludedUri", excludedUriStr.toString());
	        registration.setName("sessionFilter");
	        registration.setOrder(1);
	        return registration;
	    }
}
