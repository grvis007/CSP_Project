<%@page import="com.csp595.model.UserType"%>
<html>
<head>
<%@ page import="com.csp595.beans.UserBean" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="accounts" class="com.csp595.beans.UsersList" scope="page" /> 

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
<%
	accounts.filter();
%>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">
			
            <section id="content">

			<%if ( user.isLoggedIn()  && user.getType().equals(UserType.SALESMAN)){%>
            
            <h2>User accounts:</h2>
            	<% if(accounts.getAccounts() != null && !accounts.getAccounts().isEmpty() ){%>
                    <table style="border:0px">
                    <tr>
                    <th align="center">User ID</th>
                    <th align="center">Type of User</th>
                    <th align="center"> </th>
                    </tr>
                <%}
                for(UserBean account : accounts.getAccounts()){%>
                    <tr>     
                    <form method="post" action="editAccountSalesmanPage.jsp">
                            <td><%= account.getUserid()%>
                    			<input type="hidden" name="userid" value="<%= account.getUserid()%>">
                    		</td>
                            <td><%= account.getType().toString()%>
                   				<input type="hidden" name="usertype" value="<%= account.getType().toString()%>">
                   			</td>
                            <td>
                                <input type="submit" class="submit-button" value="Edit" />
                            </td>
                     </form>
                    </tr>
                <% }
                if(accounts.getAccounts() != null && !accounts.getAccounts().isEmpty() ){%>
                    </table>
                <%} %>
                <a href="editAccountSalesmanPage.jsp?new=true">Create new user account</a> 
                
            <%}else{%>
			    <div>You cant access this page. Please login as salesman.</div>
			<%}%>
            </section>
            
			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>