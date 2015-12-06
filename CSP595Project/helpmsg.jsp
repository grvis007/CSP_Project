<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Chicago Art Shop</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<div id="container">

	<jsp:include page="header.jsp" flush="true"/>
	
	<img class="header-image"
		src="images/img_index1.png" width="100%" height="100%"
		alt="Index Page Image" />
	<div id="body">
		<section id="content">
			<article>
				<h2>Welcome to the Chicago Art Shop Store</h2>
				<p>Make sure to login before you start shopping.</p>
			</article>
			<article class="expanded">
				<h2> Thanks for contacting us. We'll get back to you Shortly. 
	 </h2>
			</article>
		</section>
		<jsp:include page="sidebar.jsp" flush="true"/>
		<div class="clear"></div>
	</div>
	<jsp:include page="footer.jsp" flush="true"/>
</div>
</body>
</html>

