/**
 * @author pandabhi
 */

package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.amgen.anemiahub.bean.Category;
import com.amgen.anemiahub.bean.Event;
import com.amgen.anemiahub.bean.Registration;
import com.amgen.anemiahub.bean.User;
import com.amgen.anemiahub.service.HibernateUtil;
import com.amgen.anemiahub.service.UtilityService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession userSession= request.getSession(true);
		User user=(User)userSession.getAttribute("user");
		String eventId=request.getParameter("eventId").trim();
		String type=request.getParameter("type").trim();
		String result=UtilityService.registerUserToEvent(eventId, user,type);	
		response.getWriter().print(result);
	}

}
