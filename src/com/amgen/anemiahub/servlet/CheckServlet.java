package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amgen.anemiahub.bean.Category;
import com.amgen.anemiahub.bean.Document;
import com.amgen.anemiahub.bean.ShoppingCart;
import com.amgen.anemiahub.bean.User;
import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class CheckServlet
 */
@WebServlet("/CheckServlet")
public class CheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckServlet() {
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
		HttpSession session = request.getSession(true);
		List<Category> category = (List<Category>) session.getAttribute("category");
		User user = (User) session.getAttribute("user");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		List list = (List) session.getAttribute("list");
		Iterator it = list.iterator();
		String folderName = (String) session.getAttribute("folderName");
		List list1 = (List) session.getAttribute("list");
        UtilityService.addNewItem(user,cart,list);
        request.getSession().setAttribute("list", list);
		response.sendRedirect("confirmation.jsp");
	}

}
