package com.integration.boot;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务
public class Application {

	private static final Log logger = LogFactory.getLog(Application.class);

	@Bean
	public CountDownLatch closeLatch() {
		return new CountDownLatch(1); 
	}

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = new SpringApplicationBuilder()
				.sources(Application.class).web(false).run(args);
		logger.info("======dubbo-provider started successfull ======");
		CountDownLatch closeLatch = ctx.getBean(CountDownLatch.class);
		closeLatch.await();
	}
}