package com.integration.boot.kafka;

public interface KafkaProperties {
	
	final static String broker = "192.168.48.131:9092";
	final static String groupId = "group1";
	final static String topic = "topic";
	final static String kafkaServerURL = "192.168.48.131";
	final static int kafkaServerPort = 9092;
	final static int kafkaProducerBufferSize = 1024;
	final static int connectionTimeOut = 20000;
	final static int reconnectInterval = 10000;
	final static String clientId = "SimpleConsumerDemoClient";
}