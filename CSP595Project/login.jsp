
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage = "errorPage.jsp" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />  
<jsp:setProperty name="user" property="*" />
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <%
          session.setMaxInactiveInterval(1800);  // 30 minute time out 
          user.login();
          if(user.isLoggedIn())
              response.sendRedirect("homePage.jsp");
          else
              response.sendRedirect("loginPage.jsp?error=TRUE");
        %>
    </body>
</html>