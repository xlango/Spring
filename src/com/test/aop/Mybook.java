package com.test.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component(value="mybook")
public class Mybook {

	//�ٷ�������ʹ��ע�������ǿ����
	@Before(value="execution(* com.test.ioc.Book.*(..))")
	public void before() {
		System.out.println("ע�ⷽʽǰ����ǿ������������");
	}
}
