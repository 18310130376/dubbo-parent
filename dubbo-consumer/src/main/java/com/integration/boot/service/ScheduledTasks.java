package com.integration.boot.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.integration.boot.Application;

@Component
public class ScheduledTasks {
	
	 private static final Log logger = LogFactory.getLog(Application.class);


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        logger.debug("The time is now "+ dateFormat.format(new Date()));
    }
}
