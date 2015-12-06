
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage = "errorPage.jsp" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />  
<jsp:setProperty name="user" property="*" />
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <%
			session.setAttribute("loggeduser", null);
			session.invalidate();
          	user.logout();
			response.sendRedirect("homePage.jsp");
        %>
    </body>
</html>