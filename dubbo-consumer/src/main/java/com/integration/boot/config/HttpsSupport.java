package com.integration.boot.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class HttpsSupport {
	
//	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory(){
	 
	TomcatEmbeddedServletContainerFactory tomcatConfig = new TomcatEmbeddedServletContainerFactory();
	 
	tomcatConfig.addAdditionalTomcatConnectors(this.newHttpConnector());
	return tomcatConfig;
	}
	 
	private Connector newHttpConnector() {
	Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	connector.setScheme("http");
	connector.setPort(8083);
	connector.setSecure(false);
	return connector;
	 
	}

}
