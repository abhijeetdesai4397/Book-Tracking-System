package DataAccess;

import java.sql.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import DAO.Login;
public class LoginDA {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource ds)
	{
		dataSource=ds;
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	
	public Boolean checkLogin(Login l)
	{
		String sql="select username from USERS where username=? and password=?";
		String uname=jdbcTemplate.queryForObject(sql, new Object[]{l.getUsername(),l.getPassword()},String.class);
		if(uname==null)
			return false;
		System.out.println("Hi "+uname);
		return true;
	}
}
