package com.test.ioc;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component(value="user")  //相当于<bean id="user" class=""/>
@Scope(value="prototype")  //默认为单实例，先设置为多实例
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
