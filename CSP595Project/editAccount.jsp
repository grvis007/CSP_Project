<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.csp595.model.UserType" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />  
<jsp:setProperty name="user" property="*" />
 
<%
	if(user.editAccount()){
	    if(user.isLoggedIn() && user.getType() == UserType.SALESMAN){
            out.println("<BODY onLoad=\"alert('Account updated!'); window.location.href = 'manageAccountsPage.jsp';\">");                 
        }
        //If it is not a salesman, then the user is editing its on account settings
        else if(user.isLoggedIn() && user.getType() != UserType.SALESMAN){
            out.println("<BODY onLoad=\"alert('Account updated!'); window.location.href = 'homePage.jsp';\">");
        }
        //If the logged user is null, then this is a new user creating an account
        else if(!user.isLoggedIn()){
            user.login();
            out.println("<BODY onLoad=\"alert('Account created!'); window.location.href = 'homePage.jsp';\">");
        }
	} else{
	    //TODO check this section
	    if(request.getParameter("action") != null && request.getParameter("action").contains("My")){
            out.println("<BODY onLoad=\"alert('Error updating account Please verify your fields!'); window.location.href = 'editAccountPage.jsp?error=TRUE';\">");
        }
        else {
            response.sendRedirect("editAccountPage.jsp?error=TRUE");
        }
	}
%>
