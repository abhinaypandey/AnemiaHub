package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amgen.anemiahub.bean.Document;
import com.amgen.anemiahub.bean.ShoppingCart;
import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class FetchDocumentServlet
 */
@WebServlet("/FetchDocumentServlet")
public class FetchDocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchDocumentServlet() {
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
		String category = request.getParameter("selectCategory");
		String check="false";
		List<Document> document = UtilityService.getDocument(category);
		ShoppingCart cart =(ShoppingCart) session.getAttribute("cart");
		List list=(List)session.getAttribute("list");
		List<Document> doc = (List<Document>)session.getAttribute("doc");
		request.getSession().setAttribute("document", document);
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("doc", doc);
		request.getSession().setAttribute("cart", cart);
		request.getSession().setAttribute("check", check);
		response.sendRedirect("products.jsp?category=" + category);
	}

	}


