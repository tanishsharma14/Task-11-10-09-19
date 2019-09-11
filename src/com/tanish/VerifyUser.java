package com.tanish;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(
		urlPatterns = { "/VerifyUser" }, 
		initParams = { 
				@WebInitParam(name = "userid", value = "tanish"), 
				@WebInitParam(name = "password", value = "tanish123")
		})
public class VerifyUser extends HttpServlet {
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
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String type=request.getParameter("utype");
		
		
		
		if(type.equals("owner"))
		{
			ServletConfig config=this.getServletConfig();
			String name=config.getInitParameter("name");
			String pass=config.getInitParameter("password");
			
			
			if(userid.equals(name)&&password.equals(pass))
			response.sendRedirect("admin.jsp");
		}else
		{
			try {
				PreparedStatement psmt=con.prepareStatement("select userid,password from users where userid=? and password=?");
				psmt.setString(1,userid);
				psmt.setString(2,password);
				
				ResultSet rs=psmt.executeQuery();
				while(rs.next())
				{
					response.sendRedirect("buyerpage.jsp");
				}
			} catch (Exception e) {
			
				e.printStackTrace();
			}
		}
	}

}
























