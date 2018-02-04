package com.test.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component(value="mybook")
public class Mybook {

	//再方法上面使用注解完成增强配置
	@Before(value="execution(* com.test.ioc.Book.*(..))")
	public void before() {
		System.out.println("注解方式前置增强。。。。。。");
	}
}
