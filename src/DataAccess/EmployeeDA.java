package DataAccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import DAO.RequestInfo;

public class EmployeeDA {

	public void assignTask(String requestId, String employeeId)
	{
		DatabaseConnection db=new DatabaseConnection();
		CallableStatement st=null;
		//Statement st1=null;
		ResultSet rs=null;
		Connection con=null;
		con=db.getConnection();
		
		if(con!=null)
		{
			try
			{
				st=con.prepareCall("{call TRACK_EMPLOYEE(?,?,?)}");
				st.setInt(1, Integer.parseInt(requestId));
				st.setInt(2, Integer.parseInt(employeeId));
				st.registerOutParameter(3, Types.VARCHAR);
				st.execute();
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
	}
}
