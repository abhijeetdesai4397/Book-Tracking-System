package DataAccess;

import java.sql.*;
import DAO.Login;
public class LoginDA {
	
	public Boolean checkLogin(Login L)
	{//System.out.println("Hi");
		DatabaseConnection db=new DatabaseConnection();
		Statement st=null;
		ResultSet rs=null;
		Connection con=null;
		Boolean result=false;
		con=db.getConnection();
		//System.out.println("Hi");
		if(con!=null)
		{
			try
			{
				st=con.createStatement();
				String query="select username from USERS where username='"
				+L.getUsername()+"' and password='"+L.getPassword()+"'";
				//System.out.println(query);
				rs=st.executeQuery(query);
				//System.out.println(query);
				if(rs.next())
				{
						result=true;
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			finally
			{
				try{
					if(st!=null)
						st.close();
					if(rs!=null)
						rs.close();
					con.close();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
		}
		return result;
	}
}
