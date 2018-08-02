package com.integration.boot.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.deploy.SecurityCollection;
import org.apache.catalina.deploy.SecurityConstraint;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class TomcatConfig {

	@Bean
	public EmbeddedServletContainerFactory servletContainerFactory(){
	 
	TomcatEmbeddedServletContainerFactory tomcatConfig = new TomcatEmbeddedServletContainerFactory(){
	 
	@Override
	 
	protected void postProcessContext(Context context) {
	 
	SecurityConstraint securityConstraint = new SecurityConstraint();
	 
	securityConstraint.setUserConstraint("CONFIDENTIAL");
	 
	SecurityCollection collection = new SecurityCollection();
	collection.addPattern("/*");
	//另外还可以配置哪些请求必须走https，这表示以/home/开头的请求必须走https
	collection.addPattern("/home/*");
	 
	securityConstraint.addCollection(collection);
	 
	context.addConstraint(securityConstraint);
	}
	};
	 
	tomcatConfig.addAdditionalTomcatConnectors(this.newHttpConnector());
	return tomcatConfig;
	}
	 
	private Connector newHttpConnector() {
	 
	Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
	connector.setScheme("http");
	connector.setPort(8081);
	connector.setSecure(false);
	//如果只需要支持https访问，这里把收到的http请求跳转到https的端口
	connector.setRedirectPort(8443);
	return connector;
	}
}
