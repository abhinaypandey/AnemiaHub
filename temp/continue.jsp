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
<title>Insert title here</title>

</head>
<script type="text/javascript">
    function getTaskId(one){
        var id = this.value;
        var removeItem = document.getElementById("removeItem");
        removeItem.value = one;
        document.frm3.submit();
    }
</script>
<body>
<div><jsp:include page="head.jsp"></jsp:include></div>
	<%

	    List<Category> category = (List<Category>) session.getAttribute("category");
		User user = (User) session.getAttribute("user");
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
		List<Document> list = (List<Document>) session.getAttribute("list");
		String check = (String) session.getAttribute("check");
	%>
	<%
		System.out.println("items in cart in list......" + list.size());
		session.setAttribute("list", list);
		out.println("items " + list.size());
	
	%>
	<center>
		<form name="form1" method="post" action="category.jsp">
						Want to Add more products:-<input type="submit" name="continue"
							value="continue" />
					</form>
					<br><br>
		
	</center>
	<br>
	<br>
	<br>
	<br>
	<form name="frm3" method="post" action="${pageContext.request.contextPath}/RemoveItemServlet">
	<input type="hidden" id ="removeItem" name="removeItem"/>
	<center>
		<table border="1">
		
	       <%  
			if(list!=null){ %>
			<th><center>Items in Cart</center></th>
			<tr>
				<td>Total Items:</td>
				<td><%=list.size()%></td>
			</tr>
			<% 
			 for (int i = 0; i < list.size(); i++) {
			 String docName = list.get(i).getDocumentName();		
			%>
			<tr>
				<td>Cart Items:</td>
				<td><%=list.get(i).getFile()%><input type="hidden"
						name="docs" value="<%=docName%>"></td>
						<td><input type=button id="btn" name="remove" onClick="getTaskId('<%=docName%>');" value="Remove Item" ><td>
				
			</tr>
			<%} }  else { %>
			 <tr>
				<td>Total Items:</td>
				<td><%=list.size()%></td>
			</tr>
			<%for(int j = 0;j < list.size(); j++){
			 %>
		  <tr>
				<td>Cart Items:</td>
				<td><input type="text" name="docs" value="<%=list.get(j).getDocumentName()%>"></td>
				<td><input type=submit name="remove" value="Remove Item"><td>
			</tr>
			<% } }%>	
			
		
		</table>
		
	</center>
</form>

<form name="form2" method="post"
	action="${pageContext.request.contextPath}/CheckServlet">
	<input type="submit" name="Checkout" value="Checkout">
</form>
</body>
</html>