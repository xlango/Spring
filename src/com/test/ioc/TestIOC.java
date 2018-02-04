package com.test.ioc;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestIOC {

	@Test
	public void testUer() {
		//����spring�����ļ������ݴ�������
		ApplicationContext context=
				new ClassPathXmlApplicationContext("bean.xml");
		//�õ����ô����Ķ���
		User user=(User) context.getBean("user");
		System.out.println(user);
		user.add();
		
	}
	
	@Test
	public void testService() {
		//����spring�����ļ������ݴ�������
		ApplicationContext context=
				new ClassPathXmlApplicationContext("beanaop.xml");
		//�õ����ô����Ķ���
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
