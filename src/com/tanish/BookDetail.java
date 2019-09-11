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

/**
 * Servlet implementation class BookDetail
 */
@WebServlet("/BookDetail")
public class BookDetail extends HttpServlet {
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
		String id=request.getParameter("id");
		
		boolean flag=false;
		Cookie ck[]=request.getCookies();
		
		Integer viewcount=0;
		
		if(ck!=null)
		{
		for(Cookie c:ck)
		{
			if(c.getName().equals("price"+id))
			{
				viewcount=Integer.parseInt(c.getValue());
				viewcount++;
				Cookie cookie=new Cookie("price"+id,viewcount.toString());
				cookie.setMaxAge(2000);
				response.addCookie(cookie);
				flag=true;
			}
		}
		}
		if(flag==false)
		{
			Cookie cookie=new Cookie("price"+id,"1");
			cookie.setMaxAge(2000);
			response.addCookie(cookie);
			viewcount++;
		}
		out.println("<html><body>");
		out.println("<table>");
		try {
			PreparedStatement psmt=con.prepareStatement("select *from book where book_id=?");
			psmt.setString(1,id);
			ResultSet rs=psmt.executeQuery();
			
			while(rs.next())
			{
				out.println("<tr><th>Book Id</th>   <th>Book name</th>  <th>Book Subject</th>  <th>Book Price</th></tr>");
				out.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td>");
				
				if(viewcount<5)
				{
					
					out.println("<td>"+rs.getString(4)+"</td></tr>");
					
					
				}
				else
				{
					out.println("<td>"+ (((Integer.parseInt(rs.getString(4)))*110)/100)+"</td></tr>");
				}
			}
			out.println("</table>");
			
			out.println("<br>");
			out.println("<a href=CartManager?id="+id+">");
			out.println("Add to cart");
			out.println("</a>");
			//out.println(viewcount);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}




















