package DataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import DAO.EmployeeUpdate;

public class EmployeeDeleteDA {
	public boolean deleteEmployee(EmployeeUpdate eu)
	{//System.out.println("Hi");
		DatabaseConnection db=new DatabaseConnection();
		Statement st=null;
		ResultSet rs=null;
		Connection con=null;
		Boolean result=false;
		con=db.getConnection();
		System.out.println("Hi");
		
	//	EmployeeUpdate eu2 = new EmployeeUpdate();
		
		if(con!=null)
		{
			try
			{
				st=con.createStatement();
//				String query="delete from employee_tracking where employee_id='"
//				+ eu.getEmployeeId()+"'";
//			
//				System.out.println("Reached in EmployeeDeleteDA");
//				System.out.println(query);
//				//rs=st.executeQuery(query);
//				st.executeUpdate(query);
//				
				String query="delete from student_employees where employee_id='"
						+ eu.getEmployeeId()+"'";
				st.executeUpdate(query);
				con.commit();
//				while(rs.next())
//				{
//						//System.out.println("Hiiiiiiii");
//						String id=""+rs.getInt("student_id");
//						eu2.setEmployeeId(id);
//						System.out.println(eu2.getEmployeeId());
//						eu2.setEmployeeEmail(rs.getString("student_email"));
//						eu2.setEmployeeName(rs.getString("employee_name"));
//						eu2.setEmployeeStatus(rs.getString("employee_status"));
//				}
				/*if(rs.next())
				{
						return true;
				}*/
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
		return true;
	}
	
	

}
