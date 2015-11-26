
<style>
li{
	display:inline;
	padding:5px;
	
}

</style>
<ul>
	<li><a href="category.jsp">Home</a></li>
	<li><a href="eventspage.jsp">Events</a></li>
	<li><a href="category.jsp">Category</a></li>
</ul>

<ul style="float:right;margin-top:-50px;">
	<li>
		<form name="frm" method="post" action="<%=response.encodeURL("LogoutServlet") %>">
			<input type =submit  value="Logout" style="float: right;">
		</form>
	<li>
	
<ul>