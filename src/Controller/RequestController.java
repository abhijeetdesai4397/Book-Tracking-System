package Controller;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Book;
import DAO.RequestInfo;
import DataAccess.RequestDA;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
/**
 * Servlet implementation class RequestController
 */
@WebServlet("/RequestController")
public class RequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestController() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html");
    	RequestDA requestDA=new RequestDA();
    	List<RequestInfo> requestList=requestDA.getRequests();
    	HttpSession session=request.getSession();
    	session.setAttribute("requestList", requestList);
    	response.sendRedirect("pages/showRequests.jsp");
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String page=request.getParameter("request");
		if(page.equals("new"))
		{
			RequestInfo requestInfo=new RequestInfo();
			requestInfo.setStudentId(request.getParameter("studentid"));
			requestInfo.setStudentName(request.getParameter("studentname"));
			
			Book book=new Book();
			book.setBookName(request.getParameter("bookname"));
			book.setBookAuthor(request.getParameter("author"));
			book.setBookPublisher(request.getParameter("publisher"));
			book.setBookYear(request.getParameter("year"));
			
			RequestDA requestDA=new RequestDA();
			if(requestDA.addRequest(requestInfo, book))
			{
				response.sendRedirect("pages/searchBook.html");
			}
			else
				response.sendRedirect("pages/requestForm.html");
		}
		if(page.equals("search"))
		{
			String studentID=request.getParameter("studentid");
			RequestDA requestDA=new RequestDA();
			List<RequestInfo> studentList=requestDA.getStudentRequests(studentID);
			HttpSession session=request.getSession();
			session.setAttribute("studentList", studentList);
			response.sendRedirect("pages/searchRequests.jsp");
		}
		if(page.equals("csv"))
		{
			RequestDA requestDA=new RequestDA();
	    	List<RequestInfo> requestList=requestDA.getRequests();
	    	/*Workbook wb = new HSSFWorkbook();
			Sheet userSheet = wb.createSheet("UserList");
			Row headerRow = userSheet.createRow(0);
			Cell requestIDCell = headerRow.createCell(0);
			Cell bookIDCell = headerRow.createCell(1);
			Cell bookNameCell = headerRow.createCell(0);
			
			Iterator<RequestInfo> itr=requestList.iterator();
			int row=1;
			while(itr.hasNext())
			{
				RequestInfo r=(RequestInfo)itr.next();
				Row dataRow = userSheet.createRow(row);
				Cell dataCell1 = dataRow.createCell(0);
				dataCell1.setCellValue(r.getRequestId());
				
				Cell dataCell2 = dataRow.createCell(1);
				dataCell2.setCellValue(r.getBookId());
				
				Cell dataCell3 = dataRow.createCell(1);
				dataCell3.setCellValue(r.getBookName());
				row++;
			}
			FileOutputStream fileOut = new FileOutputStream("c:\\UserSheet.xls");
			wb.write(fileOut);
			fileOut.close();*/
	    	
	    	response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition","attachment;filename=temp.csv");
	        ServletOutputStream out = response.getOutputStream();
			StringBuffer sb = generateCsvFileBuffer(requestList);
	        
			InputStream in = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));
					
			byte[] outputByte = new byte[4096];
			//copy binary contect to output stream
			while(in.read(outputByte, 0, 4096) != -1)
			{
				out.write(outputByte, 0, 4096);
			}
			in.close();
			out.flush();
			out.close();
			HttpSession session=request.getSession();
	    	session.setAttribute("requestList", requestList);
	    	response.sendRedirect("pages/showRequests.jsp");
		}
	}
	
	private static StringBuffer generateCsvFileBuffer(List<RequestInfo> requestList)
	{
		StringBuffer writer = new StringBuffer();
		writer.append("Request ID");
		writer.append(',');
		writer.append("Book ID");
		writer.append(',');
		writer.append("Book Name");
		writer.append('\n');
		Iterator<RequestInfo> itr=requestList.iterator();
		while(itr.hasNext())
		{
			RequestInfo r=itr.next();
			writer.append(r.getRequestId());
			writer.append(',');
			writer.append(r.getBookId());
			writer.append(',');
			writer.append(r.getBookName());
			writer.append('\n');
		}
		return writer;
	}

}
