package DataAccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

import DAO.Book;
import DAO.RequestInfo;

public class RequestDA {

	public Boolean addRequest(RequestInfo requestInfo,Book book)
	{
		DatabaseConnection db=new DatabaseConnection();
		CallableStatement st=null;
		CallableStatement st1=null;
		ResultSet rs=null;
		Connection con=null;
		Boolean result=false;
		con=db.getConnection();
		
		if(con!=null)
		{
			try
			{
				st=con.prepareCall("{call ADD_BOOK(?,?,?,?,?)}");
				st.setString(1, book.getBookName());
				st.setString(2, book.getBookAuthor());
				st.setString(3, book.getBookPublisher());
				st.setString(4, book.getBookYear());
				st.registerOutParameter(5,Types.INTEGER);
				st.execute();
				int bookID=st.getInt(5);
				if(bookID!=0)
				{
					st1=con.prepareCall("{call ADD_REQUEST(?,?,?,?)}");
					st1.setString(1, requestInfo.getStudentId());
					st1.setString(2, requestInfo.getStudentName());
					st1.setInt(3, bookID);
					st1.registerOutParameter(4, Types.VARCHAR);
					st1.execute();
					if(st1.getString(4).equals("001"))
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
					if(st1!=null)
						st1.close();
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
	
	public List<RequestInfo> getRequests()
	{
		DatabaseConnection db=new DatabaseConnection();
		Statement st=null;
		//CallableStatement st1=null;
		ResultSet rs=null;
		Connection con=null;
		con=db.getConnection();
		List<RequestInfo> requestList=new ArrayList<RequestInfo>();
		if(con!=null)
		{
			try
			{
				st=con.createStatement();
				String query="select * from INCOMPLETEREQUEST";
				rs=st.executeQuery(query);
				while(rs.next())
				{
					RequestInfo r=new RequestInfo();
					r.setBookId(""+rs.getInt("BOOK_ID"));
					r.setBookName(rs.getString("BOOK_NAME"));
					r.setRequestId(""+rs.getInt("REQ_ID"));
					r.setStudentName(rs.getString("STUDENT_NAME"));
					requestList.add(r);
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
		return requestList;
	}
	
	public List<RequestInfo> getStudentRequests(String studentid)
	{
		DatabaseConnection db=new DatabaseConnection();
		Statement st=null;
		//CallableStatement st1=null;
		ResultSet rs=null;
		Connection con=null;
		con=db.getConnection();
		List<RequestInfo> requestList=new ArrayList<RequestInfo>();
		if(con!=null)
		{
			try
			{
				st=con.createStatement();
				String query="select BOOK_NAME, STUDENT_NAME,BOOK_STATUS from BOOKS join REQUESTS "
							+ "on ( BOOKS.BOOK_ID=REQUESTS.BOOK_ID and STUDENT_ID='"+studentid+"')";
				rs=st.executeQuery(query);
				while(rs.next())
				{
					RequestInfo r=new RequestInfo();
					r.setBookName(rs.getString("BOOK_NAME"));
					r.setStudentName(rs.getString("STUDENT_NAME"));
					r.setStatus(rs.getString("BOOK_STATUS"));
					requestList.add(r);
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
		return requestList;
	}
}
