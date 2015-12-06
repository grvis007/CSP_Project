<%@page import="com.csp595.model.UserType"%>
<html>
<head>
<%@ page import="com.csp595.model.CatalogItem" %>
<%@ page import="com.csp595.beans.ProductBean" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="productList" class="com.csp595.beans.ProductList" scope="request" /> 
<jsp:setProperty name="user" property="*"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
<%
productList.filter();
%>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">
			
            <section id="content">

			<%if ( user.isLoggedIn()  && user.getType().equals(UserType.MANAGER)){%>
            
            <h2>Products:</h2>
                <% if(productList.getItems() != null && !productList.getItems().isEmpty() ){%>
                    <table style="border:0px">
                    <tr></tr>
                    <tr>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th align="center"> </th>
                    </tr>
                 <%}
                for(CatalogItem product : productList.getItems() ){%>
                    <tr>     
                    <form method="post" action="editProductPage.jsp">
                            <td><%=product.getShortDescription() %></td>
                            <td>U$ <%=product.getCost() %></td>
                            <td>
                    			<input type="hidden" name="itemID" value="<%= product.getItemID()%>">
                                <input type="submit" value="Edit" />
                            </td>
                     </form> 
                    </tr>
                <% }
                if(productList.getItems() != null && !productList.getItems().isEmpty() ){%>
                    </table>
                <%} %>
                <a href="editProductPage.jsp">Create new product</a>
                <%}else{%>
			    <div>You cant access this page. Please login as manager.</div>
			<%}%>
			
            </section>
            
			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>