package com.tanish;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartManager
 */
@WebServlet("/CartManager")
public class CartManager extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String book_id=request.getParameter("id");
		HttpSession session=request.getSession();
		ArrayList<String> alist=new ArrayList<>();
		if(session.getAttribute("bid")!=null)
		{
			alist=(ArrayList<String>) session.getAttribute("bid");
			alist.add(book_id);
			session.setAttribute("bid",alist);
		}
		else{
			alist.add(book_id);
			session.setAttribute("bid",alist);
		}
		out.println("Book added in your card");
		out.println("Total number of books in your cart:"+alist.size());
		
		
		
	}

}
