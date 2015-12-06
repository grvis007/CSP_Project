<html>
<head>
<%@ page import="com.csp595.model.CatalogItem" %>
<%@ page import="com.csp595.beans.ReviewBean" %>
<jsp:useBean id="reviews" class="com.csp595.beans.ReviewsList" scope="page" /> 
<jsp:useBean id="product" class="com.csp595.beans.ProductBean" scope="page" />
<jsp:setProperty name="product" property="*" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
</head>

<%
	String itemID = request.getParameter("productid");
	product.recoverAllFields();
  	reviews.filter(product.getItemID());
   	String onsale = product.getOnsale().equals("TRUE")? "Yes": "No";
%>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">
			
            <section id="content">
		        <table>
				        <tr>
				        	<td>
				        	<img src = "images/<%=product.getImage()%>" width = "200" height = "200" alt =<%=product.getItemName()%> >
				        	</td>
				        	<td><%=product.getLongDescription()%></td>
				        </tr>
		                 <tr>
                             <td>Name</td>
                             <td><%=product.getItemName()%></td>
                         </tr>
                         <tr>
                             <td>Category</td>
                             <td><%=product.getCategory()%></td>
                         </tr>
                         <tr>
                             <td>Price</td>
                             <td><%=product.getCost()%></td>
                         </tr>
                         <tr>
                             <td>Product on sale</td>
                             <td><%=onsale%></td>
                         </tr>                         
           </table>
            <%for(ReviewBean review : reviews.getReviews()) {
		    %>
          		<br><br><h3>Review by <%=review.getUserid()%> on <%=review.getReviewDate() %>:</h3>
           		<table>
               	<tr>
                <td>
                <table>
                       <tr>
                           <td>User ID</td>
                           <td><%=review.getUserid()%></td>
                       </tr>
                       <tr>
                           <td>User Age</td>
                           <td><%=review.getAge()%></td>
                       </tr>
                       <tr>
                           <td>User Gender</td>
                           <td><%=review.getGender()%></td>
                       </tr>
                       <tr>
                           <td>User Occupation</td>
                           <td><%=review.getOccupation()%></td>
                       </tr>
                </table>
                </td>
                
                <td>
                <table>
                		<tr>
	                   		<td>Retailer Name</td>
	                    	<td><%=review.getRetailerName()%></td>
	                    </tr>
	                    <tr>
	                        <td>Retailer Zip Code</td>
	                        <td><%=review.getRetailerZip()%></td>
	                    </tr>
	                    <tr>
	                        <td>Retailer City</td>
	                        <td><%=review.getRetailerCity()%></td>
	                    </tr>
	                    <tr>
	                        <td>Retailer State</td>
	                        <td><%=review.getRetailerState()%></td>
	                    </tr>
                </table>
                </td>
                         <tr>
                             <td>Rating</td>
                             <td><b><%=review.getRating()%> / 5</b></td>
                         </tr>
                         <tr>
                             <td>Review Date</td>
                             <td><%=review.getReviewDate()%></td>
                         </tr>
                         <tr>
                             <td>Review</td>
                             <td><%=review.getReviewText()%></td>
                         </tr>
        	</table>
            	
            <%}%>

            </section>
            
			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>
