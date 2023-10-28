package com.bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		try(PrintWriter out = response.getWriter()) {
			String spageid = request.getParameter("page");
			int pageid = Integer.parseInt(spageid);
			int total = 5;
			if(pageid == 1) {

			} else {
				pageid = pageid - 1;
				pageid = pageid * total + 1;
			}
			try {
				List<Emp> list = EmpDao.getRecords(pageid, total);
				

				out.println("<h1>Page No: "+spageid+"</h1>");
				out.println("<table border='1' cellpadding='10' cellspacing='10' width='60%' style='border-collapse: collapse; background: blueviolet; color: aliceblue;'>");
				out.println("<tr style='background: linear-gradient(to right, #f1791e,#09a6d1,#df1f57); color: aliceblue;'><th>Id</th><th>Name</th><th>Salary</th></tr>");

				for(Emp e : list) {
					out.print("<tr><td>"+e.getId()+"</td><td>"+e.getName()+"</td><td>"+e.getSalary()+"</td><tr>");
				}
				out.println("</table>");

				out.println("<a href='ViewServlet?page=1'>1</a>");
				out.println("<a href='ViewServlet?page=2'>2</a>");
				out.println("<a href='ViewServlet?page=3'>3</a>");
			} catch(SQLException e) {
				e.printStackTrace();
			}


		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


	}

}
