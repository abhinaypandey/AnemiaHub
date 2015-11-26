package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amgen.anemiahub.bean.CartItem;
import com.amgen.anemiahub.bean.Category;
import com.amgen.anemiahub.bean.ShoppingCart;
import com.amgen.anemiahub.bean.User;
import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class GetCartItemServlet
 */
@WebServlet("/GetCartItemServlet")
public class GetCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCartItemServlet() {
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
		List<Category> category = (List<Category>) request.getSession().getAttribute("category");
		User user = (User) request.getSession().getAttribute("user");
		ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("cart");
		Vector vector = (Vector)request.getSession().getAttribute("vector");
		String productName = (String) request.getSession().getAttribute("document");
		List<CartItem> items =	UtilityService.getCartInfo(user,cart);

	}
}
