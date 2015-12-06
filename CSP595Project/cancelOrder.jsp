<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.csp595.model.UserType" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />  
<jsp:useBean id="order" class="com.csp595.beans.OrderBean" scope="request" />
<jsp:setProperty name="user" property="*" />
<jsp:setProperty name="order" property="*" />
 
<%
	if(user.isLoggedIn()){
		order.cancelOrder();
		if(user.isSalesman()){
			out.println("<BODY onLoad=\"alert('Order canceled successfuly!'); window.location.href = 'manageOrdersPage.jsp';\">");
		}
		else {
		    out.println("<BODY onLoad=\"alert('Order canceled successfuly!'); window.location.href = 'myOrdersPage.jsp';\">");
		}
	}
	else {
	    response.sendRedirect("loginPage.jsp");
	}
%>
