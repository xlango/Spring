package com.test.aop;

import org.aspectj.lang.ProceedingJoinPoint;

public class UserAop {

	public void beforeaop() {
		System.out.println("前置增强。。。。。。。。。");
	}
	
	public void afteraop() {
		System.out.println("后置增强。。。。。。。。。");
	}
	
	//环绕通知
	public void aroundaop(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		//方法之前
		System.out.println("方法之前。。。。。。。。。");
		
		//执行被增强的方法
		proceedingJoinPoint.proceed();
		
		//方法之后
		System.out.println("方法之后。。。。。。。。。");
	}
}
