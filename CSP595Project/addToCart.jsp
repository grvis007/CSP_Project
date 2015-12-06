
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.csp595.model.ItemOrder" %>
<%@ page import="com.csp595.model.UserType" %>
<%@ page import="com.csp595.model.ShoppingCart" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="cart" class="com.csp595.model.ShoppingCart" scope="page" />   
<jsp:setProperty name="user" property="*" />
 
<%
if(user.isLoggedIn()){
    cart = user.getCart();
    
    String productID = request.getParameter("productid");
    if(productID != null){
        cart.addItem(productID);
	    user.setCart(cart);
	    user.persistCart();
	    response.sendRedirect("cartPage.jsp");
    }
    if(UserType.MANAGER.toString().equals(user.getType())){                    
        out.println("<BODY onLoad=\"alert('Only customers and Salesman can create orders!'); window.location.href = 'homePage.jsp';\">");
    }
    else {
        out.println("<BODY onLoad=\"alert('Error adding item to shopping cart!'); window.location.href = 'homePage.jsp';\">");
    }
}
else {
    out.println("<BODY onLoad=\"alert('Please login before you start shopping!'); window.location.href = 'loginPage.jsp';\">");
}
%>
