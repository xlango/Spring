package com.test.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {

	@Test
	public void testUer() {
		//加载spring配置文件，根据创建对象
		ApplicationContext context=
				new ClassPathXmlApplicationContext("bean.xml");
		//得到配置创建的对象
		User user=(User) context.getBean("user");
		System.out.println(user);
		user.add();
		
	}
	
	@Test
	public void testService() {
		//加载spring配置文件，根据创建对象
		ApplicationContext context=
				new ClassPathXmlApplicationContext("beanaop.xml");
		//得到配置创建的对象
		User user=(User) context.getBean("user");
		//System.out.println(userService);
		user.add();
		
	}
	
	@Test
	public void testzjaop() {
		ApplicationContext context=new 
				ClassPathXmlApplicationContext("beanbookaop.xml");
		Book book= (Book) context.getBean("book");
		book.add();
	}
	
	@Test
	public void testJdbcT() {
		ApplicationContext context=new 
				ClassPathXmlApplicationContext("beanbookaop.xml");
		UserService userService= (UserService) context.getBean("userService");
		userService.add();
	}
}
