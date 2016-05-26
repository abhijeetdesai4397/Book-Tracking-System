package DataAccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

import DAO.Book;
import DAO.RequestInfo;

public class BookDA {

	public List<Book> searchBook(Book book)
	{
		DatabaseConnection db=new DatabaseConnection();
		Statement st=null;
		ResultSet rs=null;
		Connection con=null;
		List<Book> bookList=null;
		
		con=db.getConnection();
		
		if(con!=null)
		{
			try
			{
				st=con.createStatement();
				String query="select * from BOOKS where lower(BOOK_NAME) like '%"+book.getBookName().toLowerCase()
													+"%' and lower(BOOK_AUTHOR) like '%"+book.getBookAuthor().toLowerCase()
													+"%' and lower(BOOK_PUBLISHER) like '%"+book.getBookPublisher().toLowerCase()
													+"%' and lower(BOOK_YEAR) like '%"+book.getBookYear().toLowerCase()+"%'";
				rs=st.executeQuery(query);
				bookList=new ArrayList<Book>();
				while(rs.next())
				{
						Book b=new Book();
						b.setBookId(rs.getString("BOOK_ID"));
						b.setBookName(rs.getString("BOOK_NAME"));
						b.setBookAuthor(rs.getString("BOOK_AUTHOR"));
						b.setBookPublisher(rs.getString("BOOK_PUBLISHER"));
						b.setBookYear(rs.getString("BOOK_YEAR"));
						b.setBookStatus(rs.getString("BOOK_STATUS"));
						bookList.add(b);
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
		return bookList;
	}
	
	public void addRequest(RequestInfo requestInfo)
	{
		DatabaseConnection db=new DatabaseConnection();
		CallableStatement st=null;
		ResultSet rs=null;
		Connection con=null;
		
		con=db.getConnection();
		
		if(con!=null)
		{
			try
			{
				st=con.prepareCall("{call ADD_REQUEST(?,?,?,?)}");
				st.setString(1, requestInfo.getStudentId());
				st.setString(2, requestInfo.getStudentName());
				st.setInt(3, Integer.parseInt(requestInfo.getBookId()));
				st.registerOutParameter(4, Types.VARCHAR);
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
