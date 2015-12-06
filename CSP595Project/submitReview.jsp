
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.csp595.model.UserType" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />  
<jsp:useBean id="review" class="com.csp595.beans.ReviewBean" scope="page" />
<jsp:setProperty name="review" property="*" />
 
<%
if(user.isLoggedIn()){
    if(request.getParameter("action").contains("Delete")){
        review.submitReview(user, true);
        out.println("<BODY onLoad=\"alert('Review deleted successfuly!'); window.location.href = 'viewReviewsPage.jsp?product=\""+review.getItemID()+"\"';\">");
    } else {
        review.submitReview(user, false);
        out.println("<BODY onLoad=\"alert('Review posted successfuly!'); window.location.href = 'homePage.jsp';\">");
    }
}
else {
    out.println("<BODY onLoad=\"alert('You need to login to write a review!'); window.location.href = 'loginPage.jsp';\">");
}
%>
