package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.amgen.anemiahub.bean.Document;
import com.amgen.anemiahub.bean.ShoppingCart;
import com.amgen.anemiahub.bean.User;
import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class DocumentServlet
 */
@WebServlet("/DocumentServlet")
public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DocumentServlet() {
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
		 HttpSession session= request.getSession(true);
	        String request1 = request.getParameter("addToCart");
	        String request2 = request.getParameter("download");
	        String request3 = request.getParameter("eventDocBtn");
	        String document = request.getParameter("product");
	        String eventId=(String)session.getAttribute("eventId");
	        
	    
	        Document it = UtilityService.getDocumentId(document);
	        List<Document> list = (List<Document>) session.getAttribute("list");
	        list.add(it);
	        
			    if(request1!=null) {
		           ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		           User user = (User) session.getAttribute("user");
		           String username = user.getUname();
		           request.getSession().setAttribute("list", list);
		           request.getSession().setAttribute("document", document);
		           request.getSession().setAttribute("cart", cart);
		           response.sendRedirect("continue.jsp");
		        } 
			    
		    	else if(request2!=null){
			        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			        Date date = new Date();
			        System.out.println(dateFormat.format(date));
			        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
			        int quantity =  Integer.parseInt(request.getParameter("quantity"));
			        User user = (User) session.getAttribute("user");
			        String username = user.getUname();
			        UtilityService.addDownloadDocument(document,cart,user,date);
			        request.getSession().setAttribute("list", list);
			        request.getSession().setAttribute("document", document);
			        request.getSession().setAttribute("cart", cart);
			        response.sendRedirect("continue.jsp");
		        }
		    	else if(request3!=null){
		    		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		    		
		    		User user = (User) session.getAttribute("user");
		    		System.out.println("usname--------- " +user.getUname());
		    		String username = user.getUname();
		    		System.out.println("product is------" + document);
		    	    UtilityService.addNewItem(user, cart, list);
		    		request.getSession().setAttribute("cart", cart);
		    		
		    		List<Document> docs=(List<Document>)session.getAttribute("docs");
		    		docs.remove(it);
		    		System.out.println("doc list: "+list);
		    		request.getSession().setAttribute("docs", docs);
		    		response.sendRedirect("displayevent.jsp?eventId="+eventId);
		    			
		    	}

	}

}
