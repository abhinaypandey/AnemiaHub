<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<center>
<form action="${pageContext.request.contextPath}/AddUserServlet"
			method="post">
			<table border=1 width="30%" cellpadding="7">
				<tr>
					<td>FirstName</td>
					<td><Input type="text" name="FirstName"></td>
				</tr>
				<tr>
					<td>LastName</td>
					<td><Input type="text" name="LastName"></td>
				</tr>

				<tr>
					<td>User Name</td>
					<td><Input type="text" name="userName"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><Input type="password" name="password"></td>
				</tr>

				<tr>
					<td>Email</td>
					<td><Input type="text" name="email"></td>
				</tr>
				<tr>
					<td>Contact No</td>
					<td><Input type="long" name="ContactNo"></td>
				</tr>

                 <tr>
					<td>House No</td>
					<td><Input type="int" name="houseNo"></td>
				</tr>

                <tr>
					<td>Street</td>
					<td><Input type="text" name="street"></td>
				</tr>
				
				<tr>
					<td>City</td>
					<td><Input type="text" name="city"></td>
				</tr>
				
				<tr>
					<td>State</td>
					<td><Input type="text" name="state"></td>
				</tr>
				
				<tr>
					<td>Country</td>
					<td><Input type="text" name="country"></td>
				</tr>
				
				<tr>
					<td>Zip</td>
					<td><Input type="long" name="zip"></td>
				</tr>
				
			</table>
			<br> <br> <INPUT TYPE="SUBMIT" VALUE=Submit>
		</form>
		</center>
</body>
</html>