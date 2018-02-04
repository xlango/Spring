package com.test.ioc;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class UserService {

	//注入dao：1.定义属性 2.使用注解完成对象注入 3.使用注解时不需要生成set方法
    //	@Autowired
    //	private UserDao userDao;

	//name属性中的值是你用注解方式创建的对象的value属性中的值
	@Resource(name="userDao")
	private UserDao userDao;
	
	public void add() {
		System.out.println("service......");
		userDao.add();
	}
}
