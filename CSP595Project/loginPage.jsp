
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style> <%@include file ="/styles.css"%> </style>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true"/>
		
		<div id="body">

			<section id="content">
				<% if ((request.getParameter("error") != null) ) { %>
				 	<br>User id and password incorrect!</br>
				<%} %>
				<form method="post" action="login.jsp">
					Enter your User ID and Password and click Login		
					<table style="border:0px">
					    <tr>
					        <td>User ID</td>
					        <td><input type="TEXT" size="15" name="userid" autofocus></input></td>
					    </tr>
					    <tr>
					        <td>Password</td>
					        <td><input type="PASSWORD" size="15" name="password"/></td>
					    </tr>
					    <tr>
					        <td colspan='2'>
					            <input type="submit" value="Login" />
					        </td>
					    </tr>
					 </table>
				</form>

			</section>

			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />
	</div>

</body>


</html>
