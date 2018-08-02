package com.integration.boot.elasticsearch;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;

public class ElasticsearchOperation {

	/**
	 *创建索引库并且添加数据
	 ***/
	public static void addIndex() throws IOException {

		IndexResponse response = ElasticsearchClient
				.getTransportClient()
				.prepareIndex("account", "person", "1")
				.setSource(
						XContentFactory.jsonBuilder().startObject()
								.field("userName", "张三").field("age", 24)
								.field("salary", 20000)
								.field("job;", "JAVA工程师").endObject()).get();

		System.out.println("索引名称:" + response.getIndex() + "\n类型:"
				+ response.getType() + "\n文档ID:" + response.getId()
				+ "\n当前实例状态:" + response.status());
	}

	/**
	 * 向索引库中添加json字符串
	 * **/
	public static void addIndex2() {
		String jsonStr = "{" + "\"userName\":\"张三\","
				+ "\"sendDate\":\"2017-11-30\"," + "\"msg\":\"你好李四\"" + "}";
		IndexResponse response = ElasticsearchClient.getTransportClient()
				.prepareIndex("account", "person")
				.setSource(jsonStr, XContentType.JSON).get();
		System.out.println("json索引名称:" + response.getIndex() + "\njson类型:"
				+ response.getType() + "\njson文档ID:" + response.getId()
				+ "\n当前实例json状态:" + response.status());

	}

	/**
	 * 插入Map
	 * **/
	public static void addIndex3() {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("userName", "wukang");
		map.put("salary", 50000);
		map.put("age", 29);
		IndexResponse response = ElasticsearchClient.getTransportClient()
				.prepareIndex("account", "person").setId("3").setSource(map)
				.get();
		System.out.println("map索引名称:" + response.getIndex() + "\n map类型:"
				+ response.getType() + "\n map文档ID:" + response.getId()
				+ "\n当前实例map状态:" + response.status());
	}
	
	public static void getData1() {
		GetResponse getResponse = ElasticsearchClient.getTransportClient()
				.prepareGet("account", "person", "1").get();
		System.out.println("索引库的数据:" + getResponse.getSourceAsString());
	}
	

	public static void getData2() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");
		SearchResponse myresponse = responsebuilder
				.setQuery(QueryBuilders.matchPhraseQuery("userName", "wukang"))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
			 System.out.println(hits.getHits()[i].getSource());  
		}
	}
	
	/**
	 * 对条件查询
	 * **/
	public static void getData3() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				//对于三个词wukang，wu，kang在userName字段进行查询，如果有三者中的任意一个即算匹配
				.setQuery(QueryBuilders.termsQuery("userName", "wukang","wu","kang"))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
			 System.out.println(hits.getHits()[i].getSource());  
		}
	}
	
	
	/**
	 * 对条件查询
	 * **/
	public static void getData4() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		
		 //responsebuilder.setQuery(QueryBuilders.commonTermsQuery("name", "lishici"))  
		SearchResponse myresponse = responsebuilder
				//对于三个词wukang，wu，kang在userName字段进行查询，如果有三者中的任意一个即算匹配
				.setQuery(QueryBuilders.matchAllQuery())
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
			 System.out.println(hits.getHits()[i].getSource());  
		}
	}
	
	
	
	/**
	 * 对条件查询
	 * **/
	public static void getData5() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				.setQuery(QueryBuilders.multiMatchQuery("wukang", "job","userName"))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}
	
	/**
	 * 对条件查询
	 * **/
	public static void getData6() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				.setQuery(QueryBuilders.prefixQuery("userName","wuk"))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}
	
	
	
	/**
	 * 对条件查询
	 * **/
	public static void getData7() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				.setQuery(QueryBuilders.fuzzyQuery("userName","wukang"))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}
	
	
	/**
	 * 通配符查询  ?只能匹配一个字母
	 * **/
	public static void getData8() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				.setQuery(QueryBuilders.wildcardQuery("userName","?ukang"))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}
	
	
	
	/**
	 *未有实现
	 * **/
	public static void getData9() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				.setQuery(QueryBuilders.moreLikeThisQuery(new String[]{"wukang"}))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}
	
	

	public static void getData10() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				.setQuery(QueryBuilders.rangeQuery("salary").gt(20000).lt(100000))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}
	
	
	
	
	/**
	 * 表示的就是exists过滤，对有title字段的过滤，个人是这么理解的，可能有误
	 * **/
	public static void getData11() {

		SearchRequestBuilder responsebuilder = ElasticsearchClient
				.getTransportClient().prepareSearch("account")
				.setTypes("person");

		SearchResponse myresponse = responsebuilder
				.setPostFilter(QueryBuilders.existsQuery("age"))
				.setFrom(0).setSize(10).setExplain(true).execute().actionGet();
		SearchHits hits = myresponse.getHits();
		for (int i = 0; i < hits.getHits().length; i++) {
			System.out.println(hits.getHits()[i].getSourceAsString());
		}
	}
	
	
	public static void updateData() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("salary", 50001);

		UpdateResponse updateResponse = ElasticsearchClient
				.getTransportClient().prepareUpdate("account", "person", "1")
				.setDoc(map).get();

		System.out.println("updateResponse索引名称:" + updateResponse.getIndex()
				+ "\n updateResponse类型:" + updateResponse.getType()
				+ "\n updateResponse文档ID:" + updateResponse.getId()
				+ "\n当前实例updateResponse状态:" + updateResponse.status());
	}

	public static void deleteData() {
		DeleteResponse deleteResponse = ElasticsearchClient
				.getTransportClient().prepareDelete("account", "person", "1").get();

		System.out.println("deleteResponse索引名称:" + deleteResponse.getIndex()
				+ "\n deleteResponse类型:" + deleteResponse.getType()
				+ "\n deleteResponse文档ID:" + deleteResponse.getId()
				+ "\n当前实例deleteResponse状态:" + deleteResponse.status());
	}

	public static void main(String[] args) throws IOException {
		getData11();
	}
}
