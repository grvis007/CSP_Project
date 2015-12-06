<% if ((request.getParameter("manage") != null) ) { %>
<BODY onLoad="alert('Account updated successfuly!'); window.location.href = 'manageAccount.jsp';">
<%} else {%>
<BODY onLoad="alert('Account created successfuly!'); window.location.href = 'index.jsp';">
<%}%>