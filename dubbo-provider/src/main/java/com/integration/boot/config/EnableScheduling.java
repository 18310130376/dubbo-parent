package com.integration.boot.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EnableScheduling {
	
	private Logger logger = LoggerFactory.getLogger(EnableScheduling.class);
	
	@Scheduled(cron = "0/1 * * * * ? ") // 每1秒执行一次
	public void testCron1() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info(sdf.format(new Date()) + "*********每1秒执行一次");
	}

	@Scheduled(cron = "0/2 * * * * ? ") // 每2秒执行一次
	public void testCron2() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info(sdf.format(new Date()) + "*********每2秒执行一次");
	}

	@Scheduled(cron = "0/3 * * * * ? ") // 每3秒执行一次
	public void testCron3() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info(sdf.format(new Date()) + "*********每3秒执行一次");
	}

	@Scheduled(cron = "0/4 * * * * ? ") // 每4秒执行一次
	public void testCron4() {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info(sdf.format(new Date()) + "*********每4秒执行一次");
	}

}
