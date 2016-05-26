package Controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Book;
import DAO.RequestInfo;
import DataAccess.BookDA;
import DataAccess.RequestDA;

/**
 * Servlet implementation class BookSearchController
 */
@WebServlet("/BookSearchController")
public class BookSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String page=request.getParameter("book");
		System.out.println(page);
		if(page.equals("search"))
		{
			Book book=new Book();
			book.setBookName(request.getParameter("name"));
			book.setBookAuthor(request.getParameter("author"));
			book.setBookPublisher(request.getParameter("publisher"));
			book.setBookYear(request.getParameter("year"));
			
			BookDA bookDA=new BookDA();
			List<Book> bookList=bookDA.searchBook(book);
			HttpSession session=request.getSession();
			session.setAttribute("bookList", bookList);
			response.sendRedirect("pages/showBooks.jsp");
		}
		
		if(page.equals("request"))
		{
			RequestInfo requestInfo=new RequestInfo();
			requestInfo.setBookId(request.getParameter("bookid"));
			requestInfo.setStudentId(request.getParameter("studentid"));
			requestInfo.setStudentName(request.getParameter("studentname"));
			
			BookDA bookDA=new BookDA();
			bookDA.addRequest(requestInfo);
			response.sendRedirect("pages/searchBook.html");
		}
	}
}
