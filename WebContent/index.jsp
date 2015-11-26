<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
	if(session.getAttribute("authenticated")!=null && session.getAttribute("authenticated").equals("true")){
		response.sendRedirect("category.jsp");
	}
%>
	<form name="form1" method="post" action="<%=response.encodeURL("LoginServlet") %>">
		<center>
			<h3 style="color: blue">Welcome to login page</h3>
			<b style="color: blue">Login Here</b><br>
			<table border="1" width="2" bgcolor="white"
				style="color: blue; width: 450px">
				<tr>
					<td><b>Enter User Name:</b></td>
					<td><input type="text" name="username" value=""></td>
				</tr>
				<tr>
					<td><b>Enter Password:</b></td>
					<td><input type="password" name="password" value=""></td>
				</tr>
				<tr>
					<td><input type="reset" value="Reset"></td>
					<td><input type="submit" value="Submit"></td>
				<tr>
					<td colspan="2" style="color: black">&nbsp;&nbsp;&nbsp;&nbsp;New
						User!!! &nbsp;&nbsp;<a href="register.jsp">Register Here</a>
					</td>
				</tr>
			</table>
			</center>
	</form>
	
	
</body>
</html>