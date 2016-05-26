package DataAccess;



import java.sql.*;

import DAO.*;
import DataAccess.DatabaseConnection;
//import DAO.Login;
public class EmployeeUpdateDA {
	
	public boolean updateEmployeeDetails(EmployeeUpdate eu)
	{//System.out.println("Hi");
		DatabaseConnection db=new DatabaseConnection();
		Statement st=null;
		int rs=0;
		Connection con=null;
	//	Boolean result=false;
		con=db.getConnection();
		System.out.println("Hi");
		
		//EmployeeUpdate eu2 = new EmployeeUpdate();
		
		if(con!=null)
		{
			try
			{
				st=con.createStatement();
				String query="select * from student_employees where employee_id='"
						+ eu.getEmployeeId()+"'";
				rs=st.executeUpdate(query);
				
				System.out.println("-----------");
				System.out.println(query);
				System.out.println("-----------");
				System.out.println("RS :"+rs);
				if(rs != 0)
				{
							query="update student_employees set employee_id="
							+ eu.getEmployeeId()+", employee_name='"+eu.getEmployeeName()+"', employee_email='"+eu.getEmployeeEmail()+"', employee_status='"+eu.getEmployeeStatus()+"' where employee_id="+eu.getEmployeeId()+"";
						
							System.out.println("Reached in EmployeeUpdateDA - update query");
							System.out.println(query);
							rs=st.executeUpdate(query);
						
				}
				else
				{
					query="insert into student_employees values("
							+ eu.getEmployeeId()+",'"+eu.getEmployeeName()+"','"+eu.getEmployeeEmail()+"','"+eu.getEmployeeStatus()+"')";
						
							System.out.println("Reached in EmployeeUpdateDA -- inside insert part");
							System.out.println(query);
							try {
								st.executeUpdate(query);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				}
				
				
					
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
	
	
	
	
	
	
	
	
	public EmployeeUpdate checkEmployeeByName(EmployeeUpdate eu)
	{//System.out.println("Hi");
		DatabaseConnection db=new DatabaseConnection();
		Statement st=null;
		ResultSet rs=null;
		Connection con=null;
		Boolean result=false;
		con=db.getConnection();
		System.out.println("Hi");
		
		EmployeeUpdate eu2 = new EmployeeUpdate();
		
		if(con!=null)
		{
			try
			{
				st=con.createStatement();

				System.out.println("Reached in EmployeeUpdateDA");
				String query="select * from student_employees where employee_name='"
				+ eu.getEmployeeName()+"'";
			
				System.out.println("Reached in EmployeeUpdateDA");
				System.out.println(query);
				rs=st.executeQuery(query);				
				while(rs.next())
				{
						//System.out.println("Hiiiiiiii");
						String id=""+rs.getInt("employee_id");
						eu2.setEmployeeId(id);
						System.out.println(eu2.getEmployeeId());
						eu2.setEmployeeEmail(rs.getString("employee_email"));
						eu2.setEmployeeName(rs.getString("employee_name"));
						eu2.setEmployeeStatus(rs.getString("employee_status"));
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
		return eu2;
	}
	
	
	
}
