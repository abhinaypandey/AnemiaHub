<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ page import="com.amgen.anemiahub.bean.*" %>
<%@ page import="com.amgen.anemiahub.bean.Event" %>
<%@ page import="com.amgen.anemiahub.bean.Registration" %>
<%@ page import="org.hibernate.*" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.amgen.anemiahub.service.HibernateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> 
<title>Events page</title>

<style type="text/css">
.events{
	width:600px;
	height:auto;
	background-color:whitesmoke;
	padding:5px;
	border-bottom:5px solid white;
}
</style>

<script type="text/javascript">

$(document).ready(function(){
	var year=$('.years option:selected').text();
	var month=$('.months option:selected').text();
	
	$('.search_select').change(function(){
		var id=$(this).val();
		openPage(id);
	
	});
	
	$('#search').keyup(function(){
		var servlet_url="${pageContext.request.contextPath}/EventFilterServlet";
		var searchtext=$.trim($("#search").val());
		$('.search_select option').remove();
		if(searchtext.length >= 3){
			$.ajax({
				url:servlet_url,
				type:"get",
				dataType:"json",
				data:{searchtext:searchtext},
				success:function(data){
					data=$.parseJSON(JSON.stringify(data));
					
					if(data.length!=0){
						$('.search_select').show();
						$('<option selected disabled></option>').text('Events').appendTo('.search_select');
						$.each(data,function(key,val){
							$('<option></option>').attr({'value':val['eventId']}).text(val['eventName']).appendTo('.search_select');
						
						});
					}
					else{
						$('.search_select').hide();
					}
				}
			
			});
		}
		else{
			$('.search_select').hide();
		}
		
	});

});

function register(eventId){
var url="${pageContext.request.contextPath}/RegistrationServlet";
	$.ajax({
		url:url,
		type:"get",
		dataType:"text",
		data:{eventId:eventId,type:"register"},
		success:function(data){
			window.location.href="eventspage.jsp";
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
			window.location.href="eventspage.jsp";
		}
	
	});

}

function openPage(eventId){
 window.location.href = "./displayevent.jsp?eventId="+eventId;
}

</script>
</head>
<body>
<div><jsp:include page="head.jsp"></jsp:include></div>

	<%
		User user = (User)session.getAttribute("user");
		List<Event> events=(List<Event>)session.getAttribute("events");
		
		if(events==null){
				out.println("No events found for this selection");
		}
		
	
	 %>
	 
	 <%
		String whichRadio = (String)session.getAttribute("filter");
		String r1checked = "";
		String r2checked = "";
		if(whichRadio!=null){
			if (whichRadio.equals("1")) r1checked = " checked";	
			if (whichRadio.equals("2")) r2checked = " checked";
		}
	%>

	 <div class="top_ontainer" style="width:100%;height:800px;">
		<div style="width:90%;position:relative;float:left;">
			<div style="width:50%;float:left">
				<form action="${pageContext.request.contextPath}/EventFilterServlet" method="post">
					<span>Filter by:</span><div  style="display:inline;margin:10px;">
						<input type="radio" name="filter" value="all" onchange="submit()" <%=r1checked %>>All
						<input type="radio" name="filter" value="month" <%=r2checked %>>Month</div>
						
						<div class="datefilter" style="display:inline; ">
							<select class="years" name="year" >
								<option value="0" disabled >Years</option>
								<%
								Date date=new Date();
								long currYear=1900+date.getYear();
								for(long i=1950;i<=currYear;i++){
								%>
									<option value="<%=i%>"><%=i%></option>
								<%	
								}
								%>
							</select>
							
							<select class="months" name="month" onchange="submit()">
								<option value="0" disabled>Months</option>
								<%
								SessionFactory factory=HibernateUtil.getSessionFactory();
							    Session s=factory.openSession();
							    Transaction tr=s.beginTransaction();
							    s=factory.openSession();
							    
								String[] months={"January", "February", "March", "April", "May", "June","July", "August", "September", "October", "November", "December"};
								String [] monthNum={"01","02","03","04","05","06","07","08","09","10","11","12"};
								for(int i=0;i<months.length;i++){
								%>
									<option value="<%=monthNum[i] %>" ><%=months[i] %></option>
								<%
									
								}
								 %>
							</select>
						</div>
						
				</form>
			</div>
		
			<div style="width:40%;position:relative;float:left;display:block;"><span>Search for events: </span><input id="search" type="text" name="search" >
				<select class="search_select" name="search_select" style="display:none;">
				</select>
			</div>
		
		</div>
		
		
		<div class="events_div" style="width:80%;height:80%;margin-top:20px;">
				
		<% 
		if(events!=null){
	
		for(Event event:events){%>	
		
			<div class="events" style="width:600px;height:auto;background-color:whitesmoke;padding:5px;border-bottom:5px solid white;">
				<h2><%=event.getEventName() %></h2>
				<p><b><%=event.getLocation()%></b></p>
				<h4> <%=event.getEventStartDate() %> To <%=event.getEventEndDate() %></h4>
				<p><%=event.getEventDescription() %></p>
				
				<input type="button" value="View Event" onClick='openPage("<%=event.getEventId() %>")'>
				
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
					}
					
					%>
			</div>		
		<%}}%>
				
	 	</div>
 	
 	</div>
		
</body>
</html>