package com.integration.boot.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HttpPutFormContentFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration 
@Component
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	
	@Bean
	public Filter characterEncodingFilter() {

		CharacterEncodingFilter charEncodingFilter = new CharacterEncodingFilter();
		charEncodingFilter.setEncoding("UTF-8");
		charEncodingFilter.setForceEncoding(true);
		return charEncodingFilter;
	}
	
	@Bean
    public HttpPutFormContentFilter httpPutFormContentFilter() {
        return new HttpPutFormContentFilter();
    }

}
