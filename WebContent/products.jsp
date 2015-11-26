<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.amgen.anemiahub.bean.Document"%>
<%@ page import="com.amgen.anemiahub.bean.User"%>
<%@ page import="com.amgen.anemiahub.bean.Category"%>
<%@ page import="com.amgen.anemiahub.bean.ShoppingCart"%>
<%@ page import="java.util.*"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script> 
</head>
<div><jsp:include page="head.jsp"></jsp:include></div>

	<% 
	List<Document> document = (List<Document>) session.getAttribute("document");
    User user = (User)session.getAttribute("user");
    String category = request.getParameter("category");
    ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
   // out.println("items---------  "+cart.getTotal()) ;
    %>
<% List list=(List)session.getAttribute("list");
out.println("Items  " + list.size());
  // List<Document> doc = new ArrayList<Document>(vector);
   for(int i = 0; i < list.size(); i++){
  
   }
%>
<center><h4>
  <% out.println("Selected Category: " + category); %></h4></center>
   <%
   if(document.containsAll(list)){
    document.removeAll(list);
   }
 
	for(int i=0;i<document.size();i++){ %>
   <table border="1" width=25% align="center">
   
		<tr>
			<td><form id="model1" name="model1" method="POST" action="${pageContext.request.contextPath}/DocumentServlet">
					<font size="2" face="Verdana, Arial, Helvetica, sans-serif"><strong>Products:</strong>
						<%=document.get(i).getFile()%></font><input type="hidden"
						name="product" value="<%=document.get(i).getDocumentName() %>">
						<br>
						<br>
                    <input type="hidden" name="action" value="add">
					<input type="submit" name="addToCart" value="Add To Cart">
				
				</form></td>
	</table>
	<%} %>
</body>
</html>