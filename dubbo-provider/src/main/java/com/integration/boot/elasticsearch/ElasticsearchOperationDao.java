package com.integration.boot.elasticsearch;

import org.elasticsearch.client.transport.TransportClient;

public class ElasticsearchOperationDao {
	
	public static TransportClient getESClient(){
		return ElasticsearchClient.getTransportClient();
	}

}
