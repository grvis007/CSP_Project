<html>
	<head>
		<%@ page language="java" %>
		<%@page import="java.util.*" %>
		<%@page import="java.io.*" %>
		<%@page import="java.text.*" %>
		<%@page import="javax.servlet.*" %>
		<%@page import="javax.servlet.http.*" %>
		<%@page import="com.mongodb.*"%>
		<%@page import="java.io.IOException"%>
		<%@page import="java.util.*"%>
		<%@page import="java.io.*"%>
		<%@page import="java.util.HashMap"%>
		<link rel='stylesheet' href='styl4.css' type='text/css' />
		
		
	</head>
	<body>
	<%
			String searchParameter = request.getParameter("orderId");
			
			MongoClient mongo;
			mongo = new MongoClient("localhost", 27017);
			
			// if database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
			
			// If the collection does not exists, MongoDB will create it for you
			DBCollection Purchase = db.getCollection("Purchase");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("orderno", searchParameter);
			
			DBCursor cursor = Purchase.find(searchQuery);
			boolean flag = false;
			String deliveryDate ="";
			while(cursor.hasNext()) {
				BasicDBObject searchObj = (BasicDBObject)cursor.next();
				deliveryDate = searchObj.getString("delivarydate");
				if(deliveryDate!=null){
					flag = true;
				}	
			}
			if(!flag){
				out.println("<h1>Cancellation Status</h1>");
				//out.println("<h2>Delivery:"+deliveryDate+"<h2>");
				out.println("<h2>Order Not Found<h2>");
			}     
			else{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date dateObj = new Date();
				
				Date date1 = null;
				Date date2 = null;
				
				String today=dateFormat.format(dateObj);
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
 		
		
				date1 = format.parse(deliveryDate);
				date2 = format.parse(today);
			
				long l = date1.getTime() - date2.getTime();
				long difference=0;
				difference = l / (24 * 60 * 60 * 1000);
				String dd= Long.toString(difference);
			
			out.println("<h1>Cancellation Status</h1>");
			out.println("<table border='2'>");
			
			out.println("<tr>");
			out.println("<td>Current Date</td>");
			out.println("<td>"+date2+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Delivery Date</td>");
			out.println("<td>"+date1+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Date Difference</td>");
			out.println("<td>"+dd+"</td>");
			out.println("</tr>");
			int i = Integer.valueOf(dd);
			out.println("<tr>");
			out.println("<td>Cancellation Status</td>");
			if(i<5){
				out.println("<td>Order Cannot Be Cancelled</td>");	
			}
			else{
				out.println("<td>Order Cancelled</td>");
				Purchase.remove(searchQuery);	
			}
			out.println("</tr>");
			
		
			out.println("</table>");
			}
			out.println("<a href='TrackOrder.html'>Go Back</a>");
			
			
			out.println("</body>");
			out.println("</html>");
	%>		