<%@page import="com.csp595.beans.OrderItemBean"%>
<html>
<head>
<%@ page import="com.csp595.model.UserType"%>
<%@ page import="java.text.DecimalFormat" %>
<jsp:useBean id="order" class="com.csp595.beans.OrderBean" scope="page" />
<jsp:setProperty name="order" property="*"/>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>GameSpeed</title>
<style>
<%@ include file="/styles.css"%>
</style>
<% order.loadFields();
DecimalFormat df = new DecimalFormat("#.00");
%>
</head>

<body>
	<div id="container">
		<jsp:include page="header.jsp" flush="true" />

		<div id="body">

			<section id="content">
				
				<h2>Order <b><%=order.getConfirmation()%></b> details</h2>
                <table>
                   <tr>
                       <td>User ID</td>
                       <td><%=order.getUserid()%></td>
                   </tr>
                   <tr>
                       <td>Date Order was placed</td>
                       <td><%=order.getDate()%></td>
                   </tr>
                   <tr>
                       <td>Confirmation Number</td>
                       <td><b><%=order.getConfirmation()%></b></td>
                   </tr>
                </table>
				
				<br><br><h3>Items:</h3>
                <% 
                Double totalPrice = 0.0;
                for(OrderItemBean item : order.getItems()) { 
                    String description = item.getDescription();
                    Integer quantity = item.getQuantity();
                    Double unitCost = item.getUnitCost();
                    
                    totalPrice+=(quantity*unitCost);
                 %>
                    <table>
                    <tr>
                                  <td><%=description%></td>
                                  <td>
                                  <table>
                                       <tr>
                                                      <td>Quantity</td>
                                                      <td><%=quantity%></td>
                                       </tr>
                                       <tr>
                                                      <td>Unit Cost</td>
                                                      <td>U$ <%=unitCost%></td>
                                       </tr>
                                       <tr>
                                  </table>
                                  <td>
                              </tr>
                              </table>
                <%} %>
            <br><br>
            <div align="right"><h3>Total: U$ <%=df.format(totalPrice)%></h3></div>
            <br><br>
            <% if(order.isBeforeMaxCancelationDate()){%>
                <form method="post" action="cancelOrder.jsp">
                <input type="hidden" name="orderID" value=<%= order.getOrderID()%> >
                <div align="right"><input type="submit" class="submit-button" value="Cancel Order" /></div>
                </form>
            <%} %>
            
			</section>

			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>