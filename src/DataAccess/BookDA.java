package DataAccess;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.*;

import DAO.Book;
import DAO.RequestInfo;

class BookMapper implements RowMapper<Book>
{
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Book book=new Book();
		book.setBookId(rs.getString("BOOK_ID"));
		book.setBookName(rs.getString("BOOK_NAME"));
		book.setBookAuthor(rs.getString("BOOK_AUTHOR"));
		book.setBookPublisher(rs.getString("BOOK_PUBLISHER"));
		book.setBookYear(rs.getString("BOOK_YEAR"));
		book.setBookStatus(rs.getString("BOOK_STATUS"));
		return book;
	}
}

public class BookDA {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private SimpleJdbcCall jdbcCall;
	public BookDA()
	{}
	public BookDA(DataSource dataSource)
	{
		this.dataSource=dataSource;
		jdbcTemplate=new JdbcTemplate(dataSource);
	}
	public List<Book> searchBook(Book book)
	{
		String sql="select * from BOOKS where lower(BOOK_NAME) like '%"+book.getBookName().toLowerCase()
				+"%' and lower(BOOK_AUTHOR) like '%"+book.getBookAuthor().toLowerCase()
				+"%' and lower(BOOK_PUBLISHER) like '%"+book.getBookPublisher().toLowerCase()
				+"%' and lower(BOOK_YEAR) like '%"+book.getBookYear().toLowerCase()+"%'";
		List<Book> bookList= jdbcTemplate.query(sql, new BookMapper());
		return bookList;
	}
	
	public void addRequest(RequestInfo requestInfo)
	{
		jdbcCall=new SimpleJdbcCall(dataSource).withProcedureName("ADD_REQUEST");
		MapSqlParameterSource in= new MapSqlParameterSource();
		in.addValue("V_STUDENT_ID", requestInfo.getStudentId());
		in.addValue("V_STUDENT_NAME", requestInfo.getStudentName());
		in.addValue("V_BOOK_ID", Integer.parseInt(requestInfo.getBookId()));
		
		Map<String,Object> out=jdbcCall.execute(in);
		String msg=(String)out.get("ERROR_MSG");
	}
	/*public void addRequest(RequestInfo requestInfo)
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
	}*/
}
