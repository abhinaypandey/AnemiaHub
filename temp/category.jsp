<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.amgen.anemiahub.bean.Category"%>
<%@ page import="com.amgen.anemiahub.bean.User"%>
<%@ page import="com.amgen.anemiahub.bean.ShoppingCart"%>
<%@ page import="com.amgen.anemiahub.bean.Document"%>
<%@ page import="java.util.*"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category</title>
</head>
<% 
	String user=null;
	if(session.getAttribute("user")==null){
		response.sendRedirect("index.jsp");
	}
	
	else{
		User userObj = (User) session.getAttribute("user"); 
		user=userObj.getUname();
	}
 
 	String userName=null;
 	String sessionId=null;
 	Cookie[] cookies=request.getCookies();
 	for(Cookie cookie:cookies){
 		if(cookie.getName().equals("user"))
 			userName=cookie.getValue();
 		if(cookie.getName().equals("JSESSIONID"))
 			sessionId=cookie.getValue();
 	}
%>
<div style="">
	<h3> WELCOME <%=userName%></h3>
		
	<br>
</div>

<body>
<div><jsp:include page="head.jsp"></jsp:include></div>
	<form name="form1" method="post"
		action="${pageContext.request.contextPath}/FetchDocumentServlet">
		<center>
			<%
				List<Category> category = (List<Category>) session.getAttribute("category");
				ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
				List<Document> doc = (List<Document>) session.getAttribute("doc");
			%>
             
			<%
				 List<Document> list = (List<Document>) session.getAttribute("list");
				 if (list == null) {
				 list = new ArrayList<Document>();
			     session.setAttribute("list", list);
			   }
			%>

			<table border="1" width="30%">
				<tr>
					<td><b style="color: blue">Select Category</b></td>
					<td><select  name="selectCategory" id="selectCategory" >
							<option value="0" disabled>Select Category</option>
							<%
								if(category!=null){
								
								for (int i = 0; i < category.size(); i++) {
							%>
							<option value="<%=category.get(i).getCategoryName()%>"><%=category.get(i).getCategoryName()%></option>
							<%
								}
							}
							%>
					</select>
			</table>
			<br> <br> <input type="submit" value="submit">
		</center>
	</form>
</body>
</html>