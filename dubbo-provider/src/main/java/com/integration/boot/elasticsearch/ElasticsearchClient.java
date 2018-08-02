package com.integration.boot.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticsearchClient {

	// Settings settings = Settings.builder().put("cluster.name",
	// "elasticsearch").build();
	
	private ElasticsearchClient() {

	}
	public final static String HOST = "192.168.135.133";
	public final static int PORT = 9300;

	private static TransportClient transportClient;

	@SuppressWarnings("all")
	public static TransportClient getTransportClient() {

		if (transportClient == null) {
			synchronized (ElasticsearchClient.class) {
				if (transportClient == null) {
					try {
						transportClient = new PreBuiltTransportClient(
								Settings.EMPTY)
								.addTransportAddresses(new InetSocketTransportAddress(
										InetAddress.getByName(HOST), PORT));
					} catch (UnknownHostException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return transportClient;
	}
}
