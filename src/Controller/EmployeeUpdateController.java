package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.EmployeeUpdate;
import DataAccess.EmployeeUpdateDA;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeUpdateController")
public class EmployeeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String employeeName = request.getParameter("emp_Name");
		System.out.println("employeeName "+employeeName);
		EmployeeUpdate eu = new EmployeeUpdate();
		eu.setEmployeeName(employeeName);
		System.out.println("EmpID: "+eu.getEmployeeId());
		EmployeeUpdateDA eda = new EmployeeUpdateDA(); 
		
		
		EmployeeUpdate eu2 = eda.checkEmployeeByName(eu);
		
		String ename=null;
		String eid= null;
		String estatus= null;
		String eemail= null;
		if(eu != null)
		 {
		 	ename = eu2.getEmployeeName();
		 	eid = eu2.getEmployeeId();
		 	estatus = eu2.getEmployeeStatus();
		 	eemail = eu2.getEmployeeEmail();
		 }
		
		HttpSession session = request.getSession();
		session.setAttribute("empId", eid);
		session.setAttribute("empName", ename);
		session.setAttribute("emp_email",eemail );
		session.setAttribute("emp_status", estatus);
		response.sendRedirect("pages/employeeUpdate.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
