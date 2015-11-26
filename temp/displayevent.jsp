<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="com.amgen.anemiahub.bean.*" %>
<%@ page import="org.hibernate.*" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.amgen.anemiahub.service.HibernateUtil" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Events page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> 
<script type="text/javascript">

function register(eventId){
var url="${pageContext.request.contextPath}/RegistrationServlet";
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		data:{eventId:eventId,type:"register"},
		success:function(data){
			window.location.reload();
		}
	
	});

}

function unregister(eventId){
var url="${pageContext.request.contextPath}/RegistrationServlet";
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		data:{eventId:eventId,type:"unregister"},
		success:function(data){
			window.location.reload();
		}
	
	});

}

</script>
</head>
<body>
<div><jsp:include page="head.jsp"></jsp:include></div>
	<%
		
		List<Document> list=(List<Document>)session.getAttribute("list"); 
		String eventId=request.getParameter("eventId").trim();
		SessionFactory factory=HibernateUtil.getSessionFactory();
		Session s=factory.openSession();
		Transaction tr=s.beginTransaction();
		List<Event> events=null;
		List<Document> eventDocuments=null;
		if(eventId!=null){
			events=s.createQuery("From Event where eventId = :eventId").setString("eventId", eventId).list();
			eventDocuments=new ArrayList<Document>();
			
			if(list.size()>0){
				eventDocuments=s.createQuery("FROM Document as doc where doc.event.eventId=:eventId").setString("eventId",eventId).list();
				eventDocuments.removeAll(list);
			}
			else{
				eventDocuments=s.createQuery("FROM Document as doc where doc.event.eventId=:eventId").setString("eventId",eventId).list();
			}
		}
		
		
		pageContext.setAttribute("documents", eventDocuments);
	 	
	 	session.setAttribute("list", list);
	 	User user=(User)session.getAttribute("user");
	 	session.setAttribute("user", user);
	 	session.setAttribute("eventId", eventId);
	 	session.setAttribute("docs", eventDocuments);
	 
	 %>
	 
	 <%
		if(events!=null){
		for(Event event:events){%>	
		
			<div class="events" style="width:600px;height:auto;background-color:whitesmoke;padding:5px;border-bottom:5px solid white;">
				<h2><%=event.getEventName() %></h2>
				<p><b><%=event.getLocation()%></b></p>
				<h4> <%=event.getEventStartDate() %> To <%=event.getEventEndDate() %></h4>
				<p><%=event.getEventDescription() %></p>
				
				<%
					   s=factory.openSession();
					   Long id=null;
					   boolean isRegistered=false;
					   Transaction tx=null;
					   List<Document> document = null;
					   try {
							tx = s.getTransaction();
							tx.begin();
							Query qry = s.createQuery("select id from Registration as reg where reg.user.id ='" + user.getId() + "' and "+"reg.event.eventId='"+event.getEventId()+"'");
							id= (Long) qry.uniqueResult();
							if(id!=null){
								isRegistered=true;
							}
							tx.commit();
					   } catch (Exception e) {
							if (tx != null) {
								tx.rollback();
							}
							e.printStackTrace();
					   } 
					   
					   
						Date currDate=new Date();
						if(((currDate.getTime() >= event.getEventStartDate().getTime() && currDate.getTime() <= event.getEventEndDate().getTime()) || (currDate.getTime()<=event.getEventStartDate().getTime() && currDate.getTime()<=event.getEventEndDate().getTime()) ) && !isRegistered ){
					%>
						<input id="reg_btn" type="button" value="Register" onClick='register("<%=event.getEventId()%>")' >
					
					<%}
					else if(((currDate.getTime() >= event.getEventStartDate().getTime() && currDate.getTime() <= event.getEventEndDate().getTime()) || (currDate.getTime()<=event.getEventStartDate().getTime() && currDate.getTime()<=event.getEventEndDate().getTime()) ) && isRegistered){
					%>
						<input id="reg_btn" type="button" value="Unregister" onClick='unregister("<%=event.getEventId()%>")' >	
					
					<%	
						if(eventDocuments!=null){
					%>
					<h3><b>Associated Docs</b></h3>
					<% 
							for(int i=0;i<eventDocuments.size();i++){	
						%>
							<form action="${pageContext.request.contextPath}/DocumentServlet" method="post">
							<table>
								<tr>
									<td><input type="hidden" name="product" value="<%=eventDocuments.get(i).getDocumentName() %>">
									<p><%=eventDocuments.get(i).getDocumentName()%></p></td><td><input id="<%=eventDocuments.get(i).getDocumentName() %>" type="submit" name="eventDocBtn" value="add to cart"></td>
									
								</tr>
								
							</table>
							</form>
						<%
							}
						}
					}
					%>
			
				
		</div>
	<%}}%>
		<%
			if(list.size()>0){	
		%>
			<a href="./continue.jsp"> <input type="button" value="View Cart" ></a>
		<%
			}
		 %>
		
		

</body>
</html>