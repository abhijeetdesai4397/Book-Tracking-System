package DataAccess;

import java.sql.*;
public class DatabaseConnection {
	
	public Connection getConnection()
	{
		Connection con=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","BTS","bts");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return con;
	}

}

