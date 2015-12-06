<html>
<head>

<jsp:useBean id="order" class="com.csp595.beans.OrderConfirmationBean" scope="page" />

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
				<article>
		            <h2>Order confirmation</h2><br><br>
		            <p>Thanks for shopping at GameSpeed.<p>
		            <p>This is your order confirmation number: <h2 align="center"><b><%=request.getParameter("confirmationNumber")%><b></h2></p>
		            <br><p>Your estimated delivery date is: <b><%=order.getDeliveryDate()%><b></p>
	            </article>
			</section>

			<jsp:include page="sidebar.jsp" flush="true" />

			<div class="clear"></div>
		</div>

		<jsp:include page="footer.jsp" flush="true" />

	</div>

</body>


</html>