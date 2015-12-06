
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.csp595.model.ItemOrder" %>
<%@ page import="com.csp595.model.ShoppingCart" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="cart" class="com.csp595.model.ShoppingCart" scope="page" />   
<jsp:setProperty name="user" property="*" />
 
<%
if(user.isLoggedIn()){
    cart = user.getCart();
    
    for(int i =0; i < cart.getItemsOrdered().size(); i++){
        ItemOrder item = (ItemOrder) cart.getItemsOrdered().get(i);
        String itemID = item.getItemID();
        String strQt = request.getParameter("qt_"+itemID);
        if(strQt!=null){
            Integer qt = Integer.valueOf(strQt);
            cart.setNumOrdered(itemID, qt);
        }
    }
    //cart.removeItemsWithZeroQt();
    user.setCart(cart);
    user.persistCart();
    if(((String)request.getParameter("action")).contains("checkout")){                    
        response.sendRedirect("checkoutPage.jsp");
    }
    else {
        response.sendRedirect("homePage.jsp");
    }
}
%>
