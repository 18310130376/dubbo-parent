package com.integration.boot.kafka;

import java.util.ArrayList;
import java.util.List;

public class ConsumerGroup {

	private List<KafkaDataConsumer> consumers;

	public ConsumerGroup(int consumerNum, String groupId, String topic) {
		consumers = new ArrayList<>(consumerNum);
		for (int i = 0; i < consumerNum; ++i) {
			KafkaDataConsumer consumerThread = new KafkaDataConsumer(topic,groupId,"threadName"+i);
			consumers.add(consumerThread);
		}
	}

	public void execute() {
		for (KafkaDataConsumer task : consumers) {
			new Thread(task).start();
		}
	}

}
