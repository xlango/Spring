package com.test.ioc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="user")  //�൱��<bean id="user" class=""/>
@Scope(value="prototype")  //Ĭ��Ϊ��ʵ����������Ϊ��ʵ��
public class User {

	private String username;
	private String password;
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public User() {
		super();
	}



	public User(String username) {
		super();
		this.username = username;
	}



	public void add() {
		System.out.println("add......");
	}



	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	
	
	
}
