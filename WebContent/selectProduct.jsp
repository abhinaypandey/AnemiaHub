<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div><jsp:include page="head.jsp"></jsp:include></div>
<form action="${pageContext.request.contextPath}/NewItem"
		enctype="multipart/form-data" method="post">
		<table border=1 width="30%" cellpadding="7">
			
			<tr>
				<td>FileName:</td>
				<td><Input type=text name="fname"></td>
			</tr>
			
			<tr>
				<td>File:</td>
				<td><input type="file" name="file" id="file" /></td>
			</tr>
			
		</table>
		<br />
		<br /> <input type="submit" value="Submit" />
	</form>
</body>
</html>