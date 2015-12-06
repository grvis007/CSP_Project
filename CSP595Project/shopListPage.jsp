<html>
<head>
<%@ page import="com.csp595.model.CatalogItem" %>
<jsp:useBean id="products" class="com.csp595.beans.ProductList" scope="page" /> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
</head>

<%
    String category = request.getParameter("category");
  	String onsale = request.getParameter("onsale");
  	String itemID = request.getParameter("itemID");
  	products.filter(category, onsale, itemID);
%>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">
            <section id="content">

            <%if("COMICS".equals(category)){
                out.println("<h1>Comics<h1>");
            }
            else if("MAPS".equals(category)){
                out.println("<h1>Maps<h1>");
            }
            else if("MOVIES".equals(category)){
                out.println("<h1>Movies<h1>");
            }
			else if("MUSIC".equals(category)){
                out.println("<h1>Music<h1>");
            }
			else if("NATURE".equals(category)){
                out.println("<h1>Nature<h1>");
            }
			else if("SPORTS".equals(category)){
                out.println("<h1>Sports<h1>");
            }
			else if("TELEVISION".equals(category)){
                out.println("<h1>Television<h1>");
            }%>
            <table>
            <%for(CatalogItem item : products.getItems()) {%>
            	<tr>
            	<td>
                <img src = "images/<%=item.getImage()%>" width = "200" height = "200" alt =<%=item.getShortDescription()%>>
                </td>
                <td><%=item.getShortDescription()%><br>Price: U$<%=item.getCost()%></td>
                <td>
                   <form class = "submit-button" method = "post" action = "addToCart.jsp">
                       <input type="hidden" name="productid" value=<%=item.getItemID()%>>
                       <input class = "submit-button" type = "submit" name = "AddToCart" value = "Add to cart">
                   </form>
                   <form class = "submit-button" method = "post" action = "writeReviewPage.jsp">
                       <input type="hidden" name="itemID" value=<%=item.getItemID()%>>
                       <input class = "submit-button" type = "submit" name = "writeReview" value = "Write Review">
                   </form>
                   <form class = "submit-button" method = "get" action = "viewReviewsPage.jsp">
                       <input type="hidden" name="itemID" value=<%=item.getItemID()%>>
                       <input class = "submit-button" type = "submit" name = "viewReviews" value = "View Reviews">
                   </form>
                </td>
                </tr>
            <%}%>
            </table>
            </section>
            
			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>