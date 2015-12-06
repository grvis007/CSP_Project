<html>
<head>

<%@ page import="com.csp595.model.UserType" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">

			<section id="content">
				<form method="post" action="editAccount.jsp">
					<% if ((request.getParameter("error") != null) ) { %>
					<br>Could not create user account! Please verify your
					information.<br>
					<% }%>
					<% if (user.isLoggedIn()) { %>
					Edit your account information
					<% } else {%>
					Enter the information below to create a new user account
					<%} %>
					<table>
						<tr>
							<td>User ID</td>
							<td><input type="TEXT" size="15" name="userid"
								required="true" value=<%=user.getUserid()%>></input></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="PASSWORD" size="15" name="password"
								required="true" value=<%=user.getPassword()%>></input></td>
						</tr>
						<tr>
							<td>Confirm Password</td>
							<td><input type="PASSWORD" size="15" required="true"
								name="passwordConfirmation" value=<%=user.getPassword()%>></input></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type="TEXT" size="20" name="name"
								required="true" value="<%=user.getName()%>"></input></td>
						</tr>
						<tr>
							<td>Age</td>
							<td><input type="number" name="age" min="18" max="110"
								required="true" value=<%=user.getAge()%>></input></td>
						</tr>
						<tr>
							<td>Gender</td>
							<%
								String female_checked, male_checked;
								female_checked = "female".equals(user.getGender())? "checked":"";
								male_checked = "male".equals(user.getGender())? "checked":"";
							%>
							<td><input type="radio" name="gender" value="female"
								<%=female_checked%>> Female <br> <input
								type="radio" name="gender" value="male" <%=male_checked%>>
								Male</td>
						</tr>
						<tr>
							<td>Occupation</td>
							<td><input type="TEXT" size="20" name="occupation"
								required="true" value="<%=user.getOccupation()%>"></input></td>
						</tr>
						<% if (user.isLoggedIn() && user.getType().equals(UserType.SALESMAN)) { %>
						<tr>
							<td>Type of user</td>
							<td><input type="radio" name="type" value="CUSTOMER"
								var_checked_type_CUSTOMER> Customer <br> <input
								type="radio" name="type" value="MANAGER"
								var_checked_type_MANAGER> Store Manager <br> <input
								type="radio" name="type" value="SALESMAN"
								var_checked_type_SALESMAN> Salesman</td>
						</tr>
						<%}%>
						<tr>
							<td colspan='2'>
								<% if (user.isLoggedIn()) { %> <input
								type="submit" value="Update Account" /> 
								<%} else {%> <input
								type="submit" value="Create New Account" /> <%}%>
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
