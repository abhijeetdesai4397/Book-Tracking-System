	package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EmployeeUpdate;
import DataAccess.EmployeeDeleteDA;
import DataAccess.EmployeeUpdateDA;

/**
 * Servlet implementation class EmployeeController
 */
@WebServlet("/EmployeeInfoUpdateController")
public class EmployeeInfoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInfoUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	//super.doGet(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	// TODO Auto-generated method stub
    	response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		String employeeName = request.getParameter("empName");
		String employeeId = request.getParameter("empId");
		String employeeEmail = request.getParameter("emp_email");
		String employeeStatus = request.getParameter("emp_Status");
		
		String operation = request.getParameter("operation");
		
		System.out.println("employeeEmail "+ employeeEmail);
		
		if(operation.equals("update"))
		{
			EmployeeUpdate eu = new EmployeeUpdate();
			eu.setEmployeeName(employeeName);
			eu.setEmployeeEmail(employeeEmail);
			eu.setEmployeeId(employeeId);
			eu.setEmployeeStatus(employeeStatus);
			
			EmployeeUpdateDA eda = new EmployeeUpdateDA();  
			
			boolean update_successfully = eda.updateEmployeeDetails(eu);
			if(update_successfully)
				response.sendRedirect("pages/employeeUpdate.jsp");
			else
				response.sendRedirect("pages/search.jsp");
		}
		else
		{
			EmployeeUpdate eu = new EmployeeUpdate();
			
			eu.setEmployeeId(employeeId);
			
			EmployeeDeleteDA eda = new EmployeeDeleteDA();  
			
			boolean update_successfully = eda.deleteEmployee(eu);
			if(update_successfully)
				response.sendRedirect("pages/employeeUpdate.jsp");
			else
				response.sendRedirect("pages/search.jsp");
		}
			
    }
    
    
    
}
