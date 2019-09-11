package com.tanish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/BookList")
public class BookList extends HttpServlet {
	Connection con;
	
	public void init()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String subject=request.getParameter("subject");
		
		Cookie cookie=new Cookie("mybook",subject);
		cookie.setMaxAge(2000);
		response.addCookie(cookie);
		
		try {
			PreparedStatement psmt=con.prepareStatement("select book_id,book_name from book where book_subject=?");
			psmt.setString(1,subject);
			
			ResultSet rs=psmt.executeQuery();
			
			out.println("<html><body><h2>Select Book</h2>");
			
			while(rs.next())
			{
				String id=rs.getString(1);
				String name=rs.getString(2);
				out.println("<a href=BookDetail?id="+id+">");
				out.println(name);
				out.println("</a><br>");
			}
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		out.println("<hr>");
		out.println("<a href=ViewCart>View cart</a>");
		out.println("</body></html>");
		
	}

}





















