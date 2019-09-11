package com.tanish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




@WebServlet("/ViewCart")
public class ViewCart extends HttpServlet {
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
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		if(session.getAttribute("bid")!=null)
		{
			ArrayList<String> alist=(ArrayList<String>) session.getAttribute("bid");
			
			if(request.getParameter("did")!=null)
			{
				String did=request.getParameter("did");
				alist.remove(did);
				
				session.setAttribute("bid",alist);
			}
			
			out.println("<html><body>");
			out.println("<table>");
			out.println("<tr><th>Id</th> <th>Name</th>  <th>Subject</th>  <th>Price</th></tr>");
			for(String a:alist)
			{
				try {
					PreparedStatement psmt=con.prepareStatement("select * from book where book_id=?");
					psmt.setString(1,a);
					ResultSet rs=psmt.executeQuery();
					
					
					while(rs.next())
					{
						String id=rs.getString(1);
						out.println("<tr>");
						out.println("<td>"+id+"</td>");
						out.println("<td>"+rs.getString(2)+"</td>");
						out.println("<td>"+rs.getString(3)+"</td>");
						out.println("<td>"+rs.getString(4)+"</td>");
						out.println("<td>"+"<a href=ViewCart?did="+id+">"+"Delete"+"</a>");
						out.println("</tr>");
					}
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
			}
			
			out.println("</table>");
			out.println("</body></html>");
		}
		
		
	}

}
