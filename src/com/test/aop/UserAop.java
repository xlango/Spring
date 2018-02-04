package com.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class UserAop {

	public void beforeaop() {
		System.out.println("ǰ����ǿ������������������");
	}
	
	public void afteraop() {
		System.out.println("������ǿ������������������");
	}
	
	//����֪ͨ
	public void aroundaop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		//����֮ǰ
		System.out.println("����֮ǰ������������������");
		
		//ִ�б���ǿ�ķ���
		proceedingJoinPoint.proceed();
		
		//����֮��
		System.out.println("����֮�󡣡���������������");
	}
}
