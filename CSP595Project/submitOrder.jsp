
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="order" class="com.csp595.beans.OrderConfirmationBean" scope="request" />
<jsp:setProperty name="order" property="*" />
 
<%
if(user.isLoggedIn()){
    Integer confirmationNumber = user.submitOrder();
    if(confirmationNumber != null){                    
        response.sendRedirect("orderConfirmationPage.jsp?confirmationNumber="+confirmationNumber.toString());
    }
    else {
        out.println("<BODY onLoad=\"alert('Error submiting order!'); window.location.href = 'cartPage.jsp';\">");
    }
}
else {
    out.println("<BODY onLoad=\"alert('User is not logged in!'); window.location.href = 'homePage.jsp';\">");
}
%>
