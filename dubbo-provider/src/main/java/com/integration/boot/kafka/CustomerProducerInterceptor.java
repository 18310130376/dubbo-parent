package com.integration.boot.kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class CustomerProducerInterceptor implements ProducerInterceptor<String, String> {
	
	private int errorCounter = 0;
    private int successCounter = 0;
 
    @Override
    public void configure(Map<String, ?> configs) {
    }
 
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
    	System.out.println("===into Interceptor");
    	 return new ProducerRecord<String, String>(
                 record.topic(), record.partition(), record.timestamp(), record.key(),record.value());
    }
 
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            successCounter++;
        } else {
            errorCounter++;
        }
    }
 
    @Override
    public void close() {
        System.out.println("Successful sent: " + successCounter);
        System.out.println("Failed sent: " + errorCounter);
    }

}
