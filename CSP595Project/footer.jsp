<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" /> 
<%@ page import="com.csp595.model.UserType" %>

<footer>
	
        <div class="footer-content">
            
            <% if (user.getType().equals(UserType.MANAGER)) { %>			
            <ul>
            	<li><h4>Store Manager Section</h4></li>
                <li><a href="manageProductsPage.jsp">Manage Products</a></li>
			</ul>
			<%} %>
			
			<% if (user.getType().equals(UserType.SALESMAN)) { %>			
            <ul>
            	<li><h4>Salesman Section</h4></li>
                <li><a href="manageOrdersPage.jsp">Manage Orders</a></li>
                <li><a href="manageAccountsPage.jsp">Manage Accounts</a></li>
			</ul>
			<%} %>
           
        <div class="clear"></div>
        </div>
		
        <div class="footer-bottom">
            <p>&#169; Chicago Art Shop</p>
        </div>
		
    </footer>