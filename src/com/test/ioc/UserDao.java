package com.test.ioc;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository(value="userDao")
public class UserDao {

	//得到JdbcTemple对象
	@Resource(name="jdbcTemplate") 
	private JdbcTemplate jdbcTemplate;
	
	
	public void add() {
		System.out.println("dao......");
		String sql="insert into user values(?,?,?)";
		jdbcTemplate.update(sql,"456","456","456");		
	}
}
