<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import ="java.io.File" %>
     <%@ page import="java.util.*"%>
     <%@page import = "com.amgen.anemiahub.servlet.FetchFiles" %>
     <%@ page import="com.amgen.anemiahub.bean.Document"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div><jsp:include page="head.jsp"></jsp:include></div>
<center>
<h3><font color="blue"> Items added successfully.......</font></h3>
</center>
<% List<Document> list = (List<Document>)session.getAttribute("list");
session.setAttribute("list", list);
if(list!=null){

out.println("Total Items.. "  + list.size());
}
 %>
<h3>Download your products..: </h3>
<% 
	String folderName = "";
	String errormsg = request.getParameter("msg");
	if(errormsg!=null)
	if(errormsg.equals("error")) {
		%><script>alert("Oops!! Something went wrong. Please try again later.");</script><%
	}
	else if(errormsg.equals("success")) {
		%><script>alert("File Successfully Deleted.");</script><%
	}
	else if(errormsg.equals("successup")) {
		%><script>alert("File Successfully uploaded.");</script><%
	}
 	else if(errormsg.startsWith("fld")) {
 		folderName = errormsg.substring(3);
 		session.setAttribute("folderName", folderName);
 	}
	
%>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%
File[] files = FetchFiles.getFiles(folderName);
if(files!=null){

		out.println("<table border=\"0\" cellpadding=\"3\" cellspacing=\"0\"");
		for (File fl : files) {
			out.println("<tr>");
			if(list!=null){
			for(int i=0;i<list.size();i++){
     
			if(list.get(i).getFile().equals(fl.getName())){
			if (fl.isDirectory()) {
				System.out.println("directory:");
				out.println("<td style=\"border-bottom: 1px solid #E8E8E8\"><a style=\"text-decoration:none;\" href=\"NewItem?fileName=fld"+fl.getName()+"\">"+fl.getName()+"</a></td>");
			
			} else {
				System.out.println("file:");
				System.out.println("file name is........" + fl.getName());
				out.println("<td style=\"border-bottom: 1px solid #E8E8E8\"><a href=\"NewItem?fileName="+list.get(i).getFile()+"\">"+list.get(i).getFile()+"</a></td>");
						
			}
			}else{
			System.out.println("cart is empty....");
			}
		}
		}
		}
		}
		out.println("</table>");


 %>
 
 <form name="frm4" action="${pageContext.request.contextPath}/category.jsp">
 <% list = new ArrayList<Document>();
 session.setAttribute("list", list); %>
 <input type="submit" name="back" value="Home" >
 </form> 
</body>
</html>