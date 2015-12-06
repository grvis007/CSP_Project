<%@page import="com.csp595.model.UserType"%>
<html>
<head>
<%@ page import="com.csp595.model.CatalogItem" %>
<%@ page import="com.csp595.beans.OrderBean" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="ordersList" class="com.csp595.beans.OrdersList" scope="request" /> 
<jsp:setProperty name="user" property="*"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
<%
	ordersList.filter("");
%>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">
			
            <section id="content">

			<%if ( user.isLoggedIn()  && !user.getType().equals(UserType.SALESMAN)){
			    response.sendRedirect("loginPage.jsp");
			} else if ( user.isLoggedIn()  && user.getType().equals(UserType.SALESMAN)){
			%>
            
            <h2>Manage orders:</h2>
            	<%if(!ordersList.getOrders().isEmpty()) {%>
                    <table style="border:0px">
                    <tr></tr>
                    <tr>
                    <th align="center">Confirmation Number</th>
                    <th align="center">Order Placed</th>
                    <th align="center">User ID</th>
                    </tr>
                <% }
                for(OrderBean order : ordersList.getOrders()) {%>
                    <tr>    
                    <form method="post" action="cancelOrder.jsp">
                            <td><a href="orderDetailsPage.jsp?orderID=<%= order.getOrderID()%>"><%=order.getConfirmation()%></a></td>
                            <td><%=order.getDate()%></td>
                            <td><%=order.getUserid()%>
                            </td>
                     </form> 
                    </tr>
                <% }
				if(!ordersList.getOrders().isEmpty()) {%>
	                </table>
	            <%}
	        }%>
            </section>
            
			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>