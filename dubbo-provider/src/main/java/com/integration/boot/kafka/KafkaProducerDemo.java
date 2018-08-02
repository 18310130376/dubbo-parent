package com.integration.boot.kafka;

import java.io.IOException;

public class KafkaProducerDemo {

	 public static void main(String[] args) throws IOException
	    {
	        KafkaDataProducer producerThread = new KafkaDataProducer(KafkaProperties.topic+"01");
	        producerThread.start();
	        producerThread = new KafkaDataProducer(KafkaProperties.topic+"02");
	        producerThread.start();
	    }
}