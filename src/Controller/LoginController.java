package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import DataAccess.LoginDA;
/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Login loginObj = new Login();
		loginObj.setUsername(request.getParameter("username"));
		loginObj.setPassword(request.getParameter("password"));
		LoginDA loginDA=new LoginDA();
		if(loginDA.checkLogin(loginObj))
		{
			HttpSession session=request.getSession(true);
			session.setAttribute("username", loginObj.getUsername());
			response.sendRedirect("pages/searchBook.html");			
		}	
		else
			response.sendRedirect("pages/login.jsp");
	}

}
