package com.tanish;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SaveUser
 */
@WebServlet("/SaveUser")
public class SaveUser extends HttpServlet {

	Connection con;
	
	public void init()
	{
		
		try{
		Class.forName("com.mysql.jdbc.Driver");
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/books","root","root");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String userid=request.getParameter("userid");
		String password=request.getParameter("password");
		String address=request.getParameter("address");
		String mobile=request.getParameter("mobile");
		String email=request.getParameter("email");
		
		
		try {
			PreparedStatement psmt=con.prepareStatement("insert into users values(?,?,?,?,?)");
			psmt.setString(1,userid);
			psmt.setString(2,password);
			psmt.setString(3,address);
			psmt.setString(4,mobile);
			psmt.setString(5,email);
			
			
			int res=psmt.executeUpdate();
			if(res==1)
					out.println("user data inserted successfully");
			else
					out.println("unable to insert user data");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

}
