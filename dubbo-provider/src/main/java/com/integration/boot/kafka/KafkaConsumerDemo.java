package com.integration.boot.kafka;

public class KafkaConsumerDemo {

	public static void main(String[] args) {
		ConsumerGroup consumerGroup = new ConsumerGroup(3, KafkaProperties.groupId, KafkaProperties.topic + "01");
		consumerGroup.execute();
	}
}