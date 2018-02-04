package com.test.ioc;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="userService")
public class UserService {

	//ע��dao��1.�������� 2.ʹ��ע����ɶ���ע�� 3.ʹ��ע��ʱ����Ҫ����set����
    //	@Autowired
    //	private UserDao userDao;

	//name�����е�ֵ������ע�ⷽʽ�����Ķ����value�����е�ֵ
	@Resource(name="userDao")
	private UserDao userDao;
	
	public void add() {
		System.out.println("service......");
		userDao.add();
	}
}
