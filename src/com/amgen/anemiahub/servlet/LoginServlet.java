package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amgen.anemiahub.bean.Category;
import com.amgen.anemiahub.bean.ShoppingCart;
import com.amgen.anemiahub.bean.User;
import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password= request.getParameter("password");
		boolean result = UtilityService.loginCheck(username, password);
		User user = UtilityService.getUserByUsername(username);
		List<Category> category =  UtilityService.getCategory();
		ShoppingCart cart = null ;
		if(cart==null){
			cart = new ShoppingCart();
		}
		
	
		if(null==session.getAttribute("user")){
			if(result==true){
				
				session.setAttribute("user", user);
				session.setAttribute("category", category);
				session.setAttribute("cart", cart); 
				session.setAttribute("authenticated", "true");
				session.setMaxInactiveInterval(30*60);
	            Cookie userName = new Cookie("user", user.getUname());
	            response.addCookie(userName);
				response.sendRedirect(response.encodeRedirectURL("category.jsp"));
			}else{
				RequestDispatcher dispatcher =  request.getRequestDispatcher("index.jsp");
				PrintWriter out=response.getWriter();
				out.println("<font color=red>Either username or password is wrong.</font>");
	            dispatcher.include(request, response);
			}
		}
		else{
			System.out.println("session.."+session.getId());
			response.sendRedirect(response.encodeRedirectURL("category.jsp"));
		}
	    
	}
}
