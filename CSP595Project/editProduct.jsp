<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.csp595.model.UserType" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />  
<jsp:useBean id="product" class="com.csp595.beans.ProductBean" scope="request" />
<jsp:setProperty name="user" property="*" />
<jsp:setProperty name="product" property="*" />
 
<%
	product.editProduct(request.getParameter("action").contains("Delete"));
	if(request.getParameter("action").contains("Delete")){
	    out.println("<BODY onLoad=\"alert('Product deleted!'); window.location.href = 'homePage.jsp';\">");
	}
	else {
	    out.println("<BODY onLoad=\"alert('Product updated!'); window.location.href = 'homePage.jsp';\">");
	}
%>
