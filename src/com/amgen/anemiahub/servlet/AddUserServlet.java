package com.amgen.anemiahub.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fname= request.getParameter("FirstName");
		String lname= request.getParameter("LastName"); 
		String uname= request.getParameter("userName");
		String pword= request.getParameter("password");
		String email = request.getParameter("email");
		Long contact = Long.parseLong(request.getParameter("ContactNo"));
		int house = Integer.parseInt(request.getParameter("houseNo"));
		String street = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String country = request.getParameter("country");
		Long zip = Long.parseLong(request.getParameter("zip"));
		UtilityService.addUser(fname,lname,uname,pword,email,contact,house,street,city,state,country,zip);
		UtilityService.addAddress(house,street,city,state,country,zip);
		response.sendRedirect("index.jsp");

	}

}
