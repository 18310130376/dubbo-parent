package com.integration.boot.kafka;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaDataProducer extends Thread {

	private final KafkaProducer<String, String> producer;
	private final String topic;
	private final Properties props = new Properties();
	
	private final String businessKey = "userTableDataSync";

	public KafkaDataProducer(String topic) throws IOException {
		props.put("bootstrap.servers", KafkaProperties.broker);
		props.put("key.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer",
				"org.apache.kafka.common.serialization.StringSerializer");
		
		//props.put("request.required.acks", "1");
		props.put("acks", "all");//所有follower都响应了才认为消息提交成功，即"committed"
		props.put("retries",3);
		props.put("batch.size", 90);
		props.put("linger.ms", 1);
		props.put("block.on.buffer.full", "true");
		props.put("buffer.memory", 33554432);
		
		
		List<String> interceptors = new ArrayList<>();
		interceptors.add("com.integration.boot.kafka.CustomerProducerInterceptor"); // interceptor 1
		//interceptors.add("com.integration.boot.kafka.CustomerProducerInterceptor02"); // interceptor 2
		props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
		
		//props.put("partitioner.class", "com.integration.boot.kafka.MyLogPartitioner");
		//props.put("retries", Integer.parseInt(retries.trim()));
		//props.put("batch.size", Integer.parseInt(batchSize.trim()));
		
		
//		props.load(ClassLoader.getSystemResourceAsStream("producer.properties"));
//	    ProducerConfig config = new ProducerConfig(props);
		producer = new KafkaProducer<String, String>(props);
		this.topic = topic;
	}

	@Override
	public void run() {
		int messageNo = 1;
		ProducreData producreData = null;
		while (true && messageNo<=100) {
			producreData = new ProducreData("name", messageNo);
			final String messageStr = producreData.toString();
			try {
				//默认情况下，Kafka根据传递消息的key来进行分区的分配，即hash(key) % numPartitions，如下图所示
				//new ProducerRecord<>(messageStr, partition, key, value)
				producer.send(new ProducerRecord<String, String>(topic,businessKey, messageStr),new Callback() {
					
					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
					System.out.println("producer message: partition:"+metadata.partition()+",offset:"+metadata.offset()+",topic:"+metadata.topic());
					System.out.println(messageStr);
					}
				});
				producer.flush();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			messageNo++;
		}
	}
}