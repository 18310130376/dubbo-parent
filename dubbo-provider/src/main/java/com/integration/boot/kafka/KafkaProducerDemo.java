package com.integration.boot.kafka;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaProducerDemo {

	public static void main(String[] args) throws IOException {

		ExecutorService executorService = Executors.newFixedThreadPool(20);
		KafkaDataProducer producerThread = new KafkaDataProducer(KafkaProperties.topic + "01");
		executorService.submit(producerThread);
	}
}