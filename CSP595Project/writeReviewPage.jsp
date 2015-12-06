<html>
<head>

<%@ page import="com.csp595.model.UserType" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" /> 
<jsp:useBean id="product" class="com.csp595.beans.ProductBean" scope="page" />
<jsp:useBean id="review" class="com.csp595.beans.ReviewBean" scope="request" />
<jsp:setProperty name="product" property="*"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
<% product.recoverAllFields();%>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">

			<section id="content">
	
	        <% if(user.isLoggedIn()) {%>			
              <form method="post" action="submitReview.jsp">
		      Review product:
		                          <table>
		      <tr>
		      <td>
		      <img src = "images/<%=product.getImage()%>" width = "200" height = "200" alt =<%= product.getItemName()%>>
		      </td>
		      <td><%=product.getLongDescription() %></td>
		      </tr>
                            <tr>
                                <td>Name</td>
                                <td><%= product.getItemName()%></td>
                            </tr>
                            <tr>
                                <td>Category</td>
                                <td><%= product.getCategory()%></td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td><%= product.getCost()%></td>
                            </tr>
                            <tr>
                                <td>Retailer Name</td>
                                <td><input type="text" name="retailerName" size=15 class="s" /></td>
                            </tr>
                            <tr>
                                <td>Retailer Zip Code</td>
                                <td><input type="text" name="retailerZip" size=15 class="s" /></td>
                            </tr>
                            <tr>
                                <td>Retailer City</td>
                                <td><input type="text" name="retailerCity" size=15 class="s" /></td>
                            </tr>
                            <tr>
                                <td>Retailer State</td>
                                <td><input type="text" name="retailerState" size=15 class="s" /></td>
                            </tr>
                            <tr>
                                <td>Product on sale</td>
                                <td><%=product.getOnsale()%></td>
                            </tr>
                            <tr>
                                <td>User ID</td>
                                <td><%=user.getUserid() %></td>
                            </tr>
                            <tr>
                                <td>User Age</td>
                                <td><%=user.getAge() %></td>
                            </tr>
                            <tr>
                                <td>User Gender</td>
                                <td><%=user.getGender()%></td>
                            </tr>
                            <tr>
                                <td>User Occupation</td>
                                <td><%=user.getOccupation()%></td>
                            </tr>
                            <tr>
                                <td>Rating</td>
                                <td>
                                    <select required="true" name="rating">
                                      <option value=5 selected>5</option>
                                      <option value=4>4</option>
                                      <option value=3>3</option>
                                      <option value=2>2</option>
                                      <option value=1>1</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Review Date</td>
                                <td><%=review.getTodayDate()%></td>
                            </tr>
                            <tr>
                                <td>Review</td>
                                <td><textarea name="reviewText" rows="4" cols="50"> </textarea></td>
                            </tr>
                            <tr>
                                <td colspan='2'>
                        <input type="submit" name="action" value="Submit Review" />
                        <input type="hidden" name="itemID" value="<%=product.getItemID()%>" />
                     </td>
                    </tr>
                 </table>
             </form>
             
             <%} else { %>
             
             <%} %>
			</section>

			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>
