<html>
<head>
<%@ page import="com.csp595.model.UserType" %>
<%@ page import="com.csp595.model.ItemOrder" %>
<%@ page import="java.util.List" %>
<jsp:useBean id="user" class="com.csp595.beans.UserBean" scope="session" />
  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">

			<section id="content">
				<%
				out.println("<article>");
	            out.println("<h2>Shopping Cart</h2>");
	            out.println("<p>These are the items in your shopping cart:</p> ");  
	            out.println("</article>");
	            out.println("<article class=\"expanded\">");
	            
	            if(user.isLoggedIn() && user.getCart()!= null){
                    out.println("<tr></tr>");
                    out.println("<form method=\"post\" action=\"updateCart.jsp\">");
                    out.println("<table>");
                    out.println("<tr>");
                    out.println("<th> </th>");
                    out.println("<th>Item</th>");
                    out.println("<th>Unit Price</th>");
                    out.println("<th>Quantity</th>");
                    out.println("</tr>");
                    
                    List items = user.getCart().getItemsOrdered();
                    for(Object obj : items){
                        ItemOrder item = (ItemOrder) obj;
                        out.println("<tr>");
                        out.println("<td>");
                        out.println("<img src = \"images/"+item.getImage()+"\" width = \"200\" height = \"200\">");
                        out.println("</td>");
                        out.println("<td>"+item.getShortDescription());
                        out.println("</td>");
                        
                        out.println("<td>U$ "+item.getUnitCost());
                        out.println("</td>");
                        
                        out.println("<td>");
                        out.println("       <input type=\"number\" name=\"qt_"+item.getItemID()+"\" min=\"0\" max=\"100\" value=\""+item.getNumItems()+"\"></input>");
                        out.println("</td>");
                        out.println("</tr>");
                    }
                    out.println("</table>");
                    out.println("<br><br>");
                    out.println("<input type=\"submit\" name=\"action\" value=\"Save cart and checkout\" />");
                    out.println("<input type=\"submit\" name=\"action\" value=\"Save cart and continue shopping\" />");
                    out.println("</form>");
                    
                    out.println("</article>");
                }
				%>
			</section>

			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>
