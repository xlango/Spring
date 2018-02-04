package com.test.ioc;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcTemplateTest {

	//添加操作
	@Test
	public void add() {
		//设置数据库信息
		DriverManagerDataSource dataSource=
				new DriverManagerDataSource();		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day01");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		//创建jdbcTemplate对象，设置数据
		JdbcTemplate jdbcTemplate=
				new JdbcTemplate(dataSource);
		
		//使用jdbcTemplate对象里面的方法实现操作
		//创建sql语句
		String sql="insert into phone values(?,?)";
		int rows=jdbcTemplate.update(sql,"222" ,"qwe" );
		System.out.println(rows);
	}
	
	@Test
    public void update() {
    	//设置数据库信息
    			DriverManagerDataSource dataSource=
    					new DriverManagerDataSource();		
    			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    			dataSource.setUrl("jdbc:mysql:///spring_day01");
    			dataSource.setUsername("root");
    			dataSource.setPassword("123456");
    			
    			//创建jdbcTemplate对象，设置数据
    			JdbcTemplate jdbcTemplate=
    					new JdbcTemplate(dataSource);
    			
    			//使用jdbcTemplate对象里面的方法实现操作
    			//创建sql语句
    			String sql="update user set password=? where username=?";
    			int rows=jdbcTemplate.update(sql,"sam","tom");
    			System.out.println(rows);
    }
	
	@Test
    public void delete() {
    	//设置数据库信息
    			DriverManagerDataSource dataSource=
    					new DriverManagerDataSource();		
    			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    			dataSource.setUrl("jdbc:mysql:///spring_day01");
    			dataSource.setUsername("root");
    			dataSource.setPassword("123456");
    			
    			//创建jdbcTemplate对象，设置数据
    			JdbcTemplate jdbcTemplate=
    					new JdbcTemplate(dataSource);
    			
    			//使用jdbcTemplate对象里面的方法实现操作
    			//创建sql语句
    			String sql="delete from user where username=?";
    			int rows=jdbcTemplate.update(sql,"222");
    			System.out.println(rows);
    }
	
	@Test
    public void findOneProperty() {
    	//设置数据库信息
    			DriverManagerDataSource dataSource=
    					new DriverManagerDataSource();		
    			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    			dataSource.setUrl("jdbc:mysql:///spring_day01");
    			dataSource.setUsername("root");
    			dataSource.setPassword("123456");
    			
    			//创建jdbcTemplate对象，设置数据
    			JdbcTemplate jdbcTemplate=
    					new JdbcTemplate(dataSource);
    			
    			//使用jdbcTemplate对象里面的方法实现操作
    			//创建sql语句,返回某一个值
    			String sql="select count(*) from user";
    			int count=jdbcTemplate.queryForObject(sql, Integer.class);
    			System.out.println("count="+count);
    }
	
	//Jdbc底层实现代码
	@Test
	public void testJDBC() {
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		//加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//创建链接
			conn=DriverManager.getConnection(
					"jdbc:mysql:///spring_day01","root","123456");
			//编写sql语句
			String sql="select * from user where username=?";
			//预编译sql
			psmt=conn.prepareStatement(sql);
			//设置参数值
			psmt.setString(1, "tom");
			//执行sql
			rs=psmt.executeQuery();
			//遍历结果集
			while(rs.next()) {
				//得到返回的结果集
				String username = rs.getString("username");
				String password = rs.getString("password");
				//将值放到user对象里面
				User user=new User();
				user.setUsername(username);
				user.setPassword(password);
				System.out.println(user);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				psmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	//返回一个List
	@Test
	public void testList() {
		//设置数据库信息
		DriverManagerDataSource dataSource=
						new DriverManagerDataSource();		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day01");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
				
		//创建jdbcTemplate对象，设置数据
		JdbcTemplate jdbcTemplate=
						new JdbcTemplate(dataSource);
		
		//sql语句，查询所有的user
		String sql="select * from user";
		//调用jdbcTemple里面的方法完成操作
		List<User> list=jdbcTemplate.query(sql, new MyRowMapper());
		System.out.println(list);
	}
	
	//返回一个对象
	@Test
	public void testObject() {
		//设置数据库信息
		DriverManagerDataSource dataSource=
				new DriverManagerDataSource();		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day01");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		//创建jdbcTemplate对象，设置数据
		JdbcTemplate jdbcTemplate=
				new JdbcTemplate(dataSource);
		
		//写sql语句，根据username查询
		String sql="select * from user where username=?";
		//使用jdbcTemple的方法实现
		User user=jdbcTemplate.queryForObject(sql,new MyRowMapper() ,"222");
		System.out.println(user);
		
	}
	
	//c3p0连接池底层实现
	public void testC3p0() throws PropertyVetoException {
		ComboPooledDataSource dataSource=
				new ComboPooledDataSource();
		dataSource.setDriverClass("com.mysql.jdbc.Driver");
		dataSource.setJdbcUrl("jdbc:mysql:///spring_day01");
		dataSource.setUser("root");
		dataSource.setPassword("123456");
	}
}

class  MyRowMapper implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int num) throws SQLException {
		//1.从结果集里面吧数据得到
		String username=rs.getString("username");
		String password=rs.getString("password");
		
		//2.把得到的数据封装到对象中
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
	
}
