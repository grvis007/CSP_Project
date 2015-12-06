<html>
<head>
<%@ page import="com.csp595.model.CatalogItem"%>
<%@ page import="com.csp595.model.UserType"%>
<%@ page import="java.text.DecimalFormat"%>
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
            		Integer totalItems = user.getCart().getTotalItems();
                    Double totalPrice = user.getCart().getTotalPrice();
                    DecimalFormat df = new DecimalFormat("#.00");%>
				<h2>
					Total items: <%=totalItems%>
					<br>Total price: U$ <%=df.format(totalPrice)%>
				</h2>

				<form method="post" action="submitOrder.jsp">
					<%if ((request.getParameter("error") != null) ) {
                                    out.println("                      <br>Could not perform operation! Please verify your credit card information.<br>");
                            }%>
					Enter the credit card information below:
					<table>
						<%if(user.getType().equals(UserType.SALESMAN)){
                                out.println("      <tr>"
                                        +"              <td>Customer USERID for this transaction</td>"
                                        +"              <td><input type=\"TEXT\" size=\"20\" name=\"userid\" required=\"true\"></input></td>"
                                        +"         </tr>");
                            }
                            else {
                                out.println("       <input type=\"hidden\" name=\"userid\" value=\""+user.getName()+"\">");
                            }
                    %>
						<tr>
							<td>Name on the card</td>
							<td><input type="TEXT" size="20" name="name" required="true"
								value="<%=user.getName()%>"></input></td>
						</tr>
						<tr>
							<td>Card Number</td>
							<td><input type="TEXT" size="20" name="cardNumber"
								required="true" /></td>
						</tr>
						<tr>
							<td>Expiration</td>
							<td><input type="DATE" name="expirationDate" required="true" /></td>
						</tr>
						<tr>
							<td>Security Code</td>
							<td><input type="TEXT" size="3" name="code" required="true"></input></td>
						</tr>
						<tr>
							<td colspan='2'><input type="submit" value="Submit Order" />
							</td>
						</tr>
					</table>
				</form>
			</section>

			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>