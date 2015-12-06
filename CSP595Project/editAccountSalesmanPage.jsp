<html>
<head>

<%@ page import="com.csp595.model.UserType" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="account" class="com.csp595.beans.UserBean" scope="request" />  
<jsp:setProperty name="account" property="*"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
<%
	account.loadFields();
%>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">

			<section id="content">
				<form method="post" action="editAccount.jsp">
					<% if ((request.getParameter("error") != null) ) { %>
					<br>Could not edit user account! Please verify information.<br>
					<% }%>
					<% if (user.isLoggedIn()) { %>
					Edit the account information
					<%} %>
					<table>
						<tr>
							<td>User ID</td>
							<td><input type="TEXT" size="15" name="userid"
								required="true" value=<%=account.getUserid()%>></input></td>
						</tr>
						<tr>
							<td>Password</td>
							<td><input type="PASSWORD" size="15" name="password"
								required="true" value=<%=account.getPassword()%>></input></td>
						</tr>
						<tr>
							<td>Confirm Password</td>
							<td><input type="PASSWORD" size="15" required="true"
								name="passwordConfirmation" value=<%=account.getPassword()%>></input></td>
						</tr>
						<tr>
							<td>Name</td>
							<td><input type="TEXT" size="20" name="name"
								required="true" value=<%=account.getName()%>></input></td>
						</tr>
						<tr>
							<td>Age</td>
							<td><input type="number" name="age" min="18" max="110"
								required="true" value=<%=account.getAge()%>></input></td>
						</tr>
						<tr>
							<td>Gender</td>
							<%
								String female_checked, male_checked;
								female_checked = "female".equals(account.getGender())? "checked":"";
								male_checked = "male".equals(account.getGender())? "checked":"";
							%>
							<td><input type="radio" name="gender" value="female"
								<%=female_checked%>> Female <br> <input
								type="radio" name="gender" value="male" <%=male_checked%>>
								Male</td>
						</tr>
						<tr>
							<td>Occupation</td>
							<td><input type="TEXT" size="20" name="occupation"
								required="true" value=<%=account.getOccupation()%>></input></td>
						</tr>
						<tr>
							<td>Type of user</td>
							<td><input type="radio" name="type" value="CUSTOMER"
								var_checked_type_CUSTOMER> Customer <br> <input
								type="radio" name="type" value="MANAGER"
								var_checked_type_MANAGER> Store Manager <br> <input
								type="radio" name="type" value="SALESMAN"
								var_checked_type_SALESMAN> Salesman</td>
						</tr>
						<tr>
							<td colspan='2'>
								<% if (request.getParameter("new")!=null) { %> <input
								type="submit" value="Create New Account" /> 
								<%} else {%> <input
								type="submit" value="Update Account" /> <%}%>
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
