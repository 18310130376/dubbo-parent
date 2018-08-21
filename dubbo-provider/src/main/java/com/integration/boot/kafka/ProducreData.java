package com.integration.boot.kafka;

import java.io.Serializable;

public class ProducreData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public ProducreData(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "{name:" + name + ", age:" + age + "}";
	}

}
