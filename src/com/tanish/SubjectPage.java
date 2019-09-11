package com.tanish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SubjectPage")
public class SubjectPage extends HttpServlet {
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
		try {
				
				
				out.println("<html><body><h2>Select subject</h2>");
				PreparedStatement psmt=con.prepareStatement("select distinct book_subject from book");
				ResultSet rs=psmt.executeQuery();
				
				String my="";
				
				Cookie ck[]=request.getCookies();
				
				for(Cookie c:ck)
				{
					if(c.getName().equals("mybook"))
					{
						my=c.getValue();
					}
				}
				out.println("<marquee><h4>");
				out.println("Attractive Offers On "+my);
				out.println("</h4></marquee>");
				while(rs.next())
				{
					String sub=rs.getString(1);
					out.println("<a href=BookList?subject="+sub+">");
					out.println(sub);
					out.println("</a><br>");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			out.println("<hr>");
			out.println("<a href=ViewCart>View cart</a>");		
			out.println("</body></html>");
			
			
	}

}
