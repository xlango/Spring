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

	//��Ӳ���
	@Test
	public void add() {
		//�������ݿ���Ϣ
		DriverManagerDataSource dataSource=
				new DriverManagerDataSource();		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day01");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		//����jdbcTemplate������������
		JdbcTemplate jdbcTemplate=
				new JdbcTemplate(dataSource);
		
		//ʹ��jdbcTemplate��������ķ���ʵ�ֲ���
		//����sql���
		String sql="insert into phone values(?,?)";
		int rows=jdbcTemplate.update(sql,"222" ,"qwe" );
		System.out.println(rows);
	}
	
	@Test
    public void update() {
    	//�������ݿ���Ϣ
    			DriverManagerDataSource dataSource=
    					new DriverManagerDataSource();		
    			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    			dataSource.setUrl("jdbc:mysql:///spring_day01");
    			dataSource.setUsername("root");
    			dataSource.setPassword("123456");
    			
    			//����jdbcTemplate������������
    			JdbcTemplate jdbcTemplate=
    					new JdbcTemplate(dataSource);
    			
    			//ʹ��jdbcTemplate��������ķ���ʵ�ֲ���
    			//����sql���
    			String sql="update user set password=? where username=?";
    			int rows=jdbcTemplate.update(sql,"sam","tom");
    			System.out.println(rows);
    }
	
	@Test
    public void delete() {
    	//�������ݿ���Ϣ
    			DriverManagerDataSource dataSource=
    					new DriverManagerDataSource();		
    			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    			dataSource.setUrl("jdbc:mysql:///spring_day01");
    			dataSource.setUsername("root");
    			dataSource.setPassword("123456");
    			
    			//����jdbcTemplate������������
    			JdbcTemplate jdbcTemplate=
    					new JdbcTemplate(dataSource);
    			
    			//ʹ��jdbcTemplate��������ķ���ʵ�ֲ���
    			//����sql���
    			String sql="delete from user where username=?";
    			int rows=jdbcTemplate.update(sql,"222");
    			System.out.println(rows);
    }
	
	@Test
    public void findOneProperty() {
    	//�������ݿ���Ϣ
    			DriverManagerDataSource dataSource=
    					new DriverManagerDataSource();		
    			dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    			dataSource.setUrl("jdbc:mysql:///spring_day01");
    			dataSource.setUsername("root");
    			dataSource.setPassword("123456");
    			
    			//����jdbcTemplate������������
    			JdbcTemplate jdbcTemplate=
    					new JdbcTemplate(dataSource);
    			
    			//ʹ��jdbcTemplate��������ķ���ʵ�ֲ���
    			//����sql���,����ĳһ��ֵ
    			String sql="select count(*) from user";
    			int count=jdbcTemplate.queryForObject(sql, Integer.class);
    			System.out.println("count="+count);
    }
	
	//Jdbc�ײ�ʵ�ִ���
	@Test
	public void testJDBC() {
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		//��������
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//��������
			conn=DriverManager.getConnection(
					"jdbc:mysql:///spring_day01","root","123456");
			//��дsql���
			String sql="select * from user where username=?";
			//Ԥ����sql
			psmt=conn.prepareStatement(sql);
			//���ò���ֵ
			psmt.setString(1, "tom");
			//ִ��sql
			rs=psmt.executeQuery();
			//���������
			while(rs.next()) {
				//�õ����صĽ����
				String username = rs.getString("username");
				String password = rs.getString("password");
				//��ֵ�ŵ�user��������
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
	
	//����һ��List
	@Test
	public void testList() {
		//�������ݿ���Ϣ
		DriverManagerDataSource dataSource=
						new DriverManagerDataSource();		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day01");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
				
		//����jdbcTemplate������������
		JdbcTemplate jdbcTemplate=
						new JdbcTemplate(dataSource);
		
		//sql��䣬��ѯ���е�user
		String sql="select * from user";
		//����jdbcTemple����ķ�����ɲ���
		List<User> list=jdbcTemplate.query(sql, new MyRowMapper());
		System.out.println(list);
	}
	
	//����һ������
	@Test
	public void testObject() {
		//�������ݿ���Ϣ
		DriverManagerDataSource dataSource=
				new DriverManagerDataSource();		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///spring_day01");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
		
		//����jdbcTemplate������������
		JdbcTemplate jdbcTemplate=
				new JdbcTemplate(dataSource);
		
		//дsql��䣬����username��ѯ
		String sql="select * from user where username=?";
		//ʹ��jdbcTemple�ķ���ʵ��
		User user=jdbcTemplate.queryForObject(sql,new MyRowMapper() ,"222");
		System.out.println(user);
		
	}
	
	//c3p0���ӳصײ�ʵ��
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
		//1.�ӽ������������ݵõ�
		String username=rs.getString("username");
		String password=rs.getString("password");
		
		//2.�ѵõ������ݷ�װ��������
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		
		return user;
	}
	
}
