package com.integration.boot.kafka;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

public class KafkaDataConsumer implements Runnable  {

	private KafkaConsumer<String, String> consumer;
	
	private String threadName;

	public KafkaDataConsumer(String topic,String groupId,String threadName) {
		consumer = new KafkaConsumer<String, String>(createConsumerConfig(groupId));
		
//		TopicPartition partition0 = new TopicPartition("topicName", 0);
//		consumer.assign(Arrays.asList(partition0));
//		// consumer.seek(partition0, 220);
//		consumer.seekToEnd(Arrays.asList(partition0));
		consumer.subscribe(Arrays.asList(topic),new ConsumerRebalanceListener() {
			
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
				
			}
			
			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
				
			}
		});
		this.threadName = threadName;
	}

	private Properties createConsumerConfig(String groupId ) {
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", KafkaProperties.broker);
		properties.setProperty("group.id", groupId);
		properties.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		properties.put("auto.offset.reset", "earliest");//earliest 
//		当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费 
//		latest 
//		当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据 
//		none 
//		topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
		//request.timeout.ms should be greater than session.timeout.ms and fetch.max.wait.ms
		properties.put("request.timeout.ms", "300001");
		//每次poll的最大record数，kafka 0.10之后的特性
		properties.put("max.poll.records", "300");
		return properties;
	}

	@Override
	public void run() {
		while (true) {
			try {
				ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
				System.out.println("threadName"+threadName);
				/**
				for (ConsumerRecord<String, String> recode : records) {
					System.out.println("recodeOffset=" + recode.offset()
							+ ",recodeValue=" + recode.value()+","+recode.partition());
					
					System.out.println("recodeValue=" + recode.value()+",recode.offset="+recode.offset()+",recode.partition="+recode.partition());
				}
				**/
				
		        /*********手动提交***********/		
				for(TopicPartition partition : records.partitions()) {
					List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
					for (ConsumerRecord<String, String> record : partitionRecords) {
						System.out.println("recodeValue=" + record.value()+",recode.offset="+record.offset()+",recode.partition="+record.partition()+",record="+record.topic()+",threadName="+threadName);
					}
					if(!partitionRecords.isEmpty()){
						long lastoffset = partitionRecords.get(partitionRecords.size() - 1).offset();
						consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastoffset + 1)));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}