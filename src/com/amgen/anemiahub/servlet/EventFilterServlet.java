/**
 * @author pandabhi
 */
package com.amgen.anemiahub.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.amgen.anemiahub.service.HibernateUtil;
import com.amgen.anemiahub.service.UtilityService;
import com.amgen.anemiahub.bean.Event;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
/**
 * Servlet implementation class EventFilterServlet
 */

@WebServlet("/EventFilterServlet")
public class EventFilterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventFilterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String searchText=request.getParameter("searchtext").trim();
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session s=factory.openSession();
		
		try{
			Transaction tr=s.beginTransaction();	
			String q="SELECT eventId,eventName FROM EVENT where eventName like "+"'%"+searchText+"%' or eventDesc like "+"'%"+searchText+"%'";
			SQLQuery query =s.createSQLQuery(q);
			List<Object[]> rows = query.list();
			List<Event> events=new ArrayList<Event>();
			
			for(Object [] row:rows){
				Event event =new Event();
				event.setEventId(Long.parseLong(row[0].toString()));
				event.setEventName(row[1].toString());
				events.add(event);
			}
			
			Gson gson=new Gson();
			JsonElement element = gson.toJsonTree(events, new TypeToken<List<Event>>(){}.getType());
			JsonArray jsonArray = element.getAsJsonArray();
			response.setContentType("application/json");
			response.getWriter().print(jsonArray);
			

			
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String year=request.getParameter("year").trim();
		String filter=request.getParameter("filter").trim();
		String month=request.getParameter("month").trim();
		if(filter.equals("all")){
			request.getSession().setAttribute("filter", "1");
		}
		else if(filter.equals("month")){
			request.getSession().setAttribute("filter", "2");
			
		}
		List<Event> events=new ArrayList<Event>();
		events=UtilityService.getEvents(filter, month,year);
		
		request.getSession().setAttribute("events", events);
		response.sendRedirect("eventspage.jsp");		
	}

}
