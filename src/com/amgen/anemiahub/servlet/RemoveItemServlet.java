package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amgen.anemiahub.bean.Document;
import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class RemoveItemServlet
 */
@WebServlet("/RemoveItemServlet")
public class RemoveItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveItemServlet() {
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
		String document = request.getParameter("removeItem");
		System.out.println("document .........." + document);
//		System.out.println("remove itemdff.............." + fileName);
//		Document doc = UtilityService.getDocumentName(fileName);
//		System.out.println("remove item is.............." + doc.getDocumentName());
		//String docname = doc.getDocumentName();
		Document docu = new Document();
		docu.setDocumentName(document);
		List<Document> list = (List<Document>) request.getSession().getAttribute("list");
		list.remove(docu);
		for(int i=0;i<list.size();i++){
			System.out.println("list after removing....."+ list.get(i).getDocumentName());
		}
		request.getSession().setAttribute("list", list);
		response.sendRedirect("continue.jsp");
	}

}
