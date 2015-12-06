<html>
<head>

<%@ page import="com.csp595.model.UserType" %>
<%@ page import="java.util.HashMap" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
<jsp:useBean id="product" class="com.csp595.beans.ProductBean" scope="request" /> 
<jsp:setProperty name="product" property="*"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
<%
	product.recoverAllFields();
%>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">

			<section id="content">
			
			<%if(user.isLoggedIn() && user.isManager()){ %>
				
			<form method="post" action="editProduct.jsp">
                <% if ((request.getParameter("error") != null) ) {%>
                                      <br>Could not create product!
                <%} %>
                Enter the product information below:
                        <table>
                          <tr>
                              <td>Name</td>
                              <td><input type="TEXT" size="20" required="true" name="itemName" value="<%= product.getItemName()%>"></input></td>
                          </tr>
                          <tr>
                              <td>Description</td>
                              <td><input type="TEXT" size="70" required="true" name="longDescription" value="<%= product.getLongDescription()%>"></input></td>
                          </tr>
                          <tr>
                              <td>Category</td>
                              <td>
                                  <select name="category" required="true" onchange="updateCategory()">
                                    <option value=""> </option>
                                    <option value="COMICS" <%= product.getChecked("category","COMICS")%>>Comics</option>
									<option value="MAPS" <%= product.getChecked("category","MAPS")%>>Maps</option>
									<option value="MOVIES" <%= product.getChecked("category","MOVIES")%>>Movies</option>
                                    <option value="MUSIC" <%= product.getChecked("category","MUSIC")%>>Music</option>
                                    <option value="NATURE" <%= product.getChecked("category","NATURE")%>>Nature</option>
									<option value="SPORTS" <%= product.getChecked("category","SPORTS")%>>Sports</option>
									<option value="TELEVISION" <%= product.getChecked("category","TELEVISION")%>>Television</option>
                                  </select>
                              </td>
                          </tr>                          
                          <tr>
                              <td>Price</td>
                              <td>
                                  <input type="number" name="cost" min="0.00" max="1000.00" required="true" step="0.01" value=<%= product.getCost()%>>
                              </td>
                          </tr>                          
                          <tr>                          
                              <td>On sale</td>
                              <td><input type="radio" name="onsale" value="TRUE" <%= product.getChecked("onsale","TRUE")%>> Yes
                                  <br>
                                  <input type="radio" name="onsale" value="FALSE" <%= product.getChecked("onsale","FALSE")%>> No
                              </td>
                          </tr>                          
                          <tr>
                              <td>Image</td>
                              <td><input name="image" type="TEXT" size="20" value="<%= product.getImage()%>"></input></td>
                          </tr>
                          <tr>
                          <td colspan='2'>
                <% if ((request.getParameter("itemID") != null) ) {%>
                		  <input name="itemID" type="TEXT" size="20" value="<%= product.getItemID()%>" hidden="true"></input>
                          <input type="submit" name="action" value="Update Product" />
                          <input type="submit" name="action" value="Delete Product" />
                <%} else { %>
                         <input type="submit" name="action" value="Create New Product" />
                <%} %>
                          </td>
                      </tr>
                   </table>
               </form>

			</section>
			
			<%} %>

			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>

</html>
