package DAO;

import java.sql.Date;

public class RequestInfo {
	
	private String requestId;
	private String studentId;
	private String studentName;
	private String bookId;
	private String bookName;
	private Date requestDate;
	private Date firstUpload;
	private String status;
	private Date finalUpload;
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}
	public Date getFirstUpload() {
		return firstUpload;
	}
	public void setFirstUpload(Date firstUpload) {
		this.firstUpload = firstUpload;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getFinalUpload() {
		return finalUpload;
	}
	public void setFinalUpload(Date finalUpload) {
		this.finalUpload = finalUpload;
	}
}
