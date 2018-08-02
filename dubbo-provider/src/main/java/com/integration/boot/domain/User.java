package com.integration.boot.domain;

import java.io.Serializable;


/**
 * 用户信息
 * @author Administrator
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	
	private int id;
	
	private String name;
	
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User() {
		super();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

	public User(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

}
