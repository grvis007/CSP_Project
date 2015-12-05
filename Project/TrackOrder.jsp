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
		
	</head>
	<body>
	<%
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		
		out.println("<html>");
			out.println("<head>");
			out.println("<link rel='stylesheet' href='styl4.css' type='text/css' />");
			
			out.println("</head>");
			out.println("<body>");
			//out.println("<h1> Item shipped</h1>");
			
			String searchParameter = request.getParameter("orderId");
			
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
				out.println("<h1>Order Tracking</h1>");
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
			
				
			long difference=0;
			long l = date1.getTime() - date2.getTime();
			difference = l / (24 * 60 * 60 * 1000);
			String dd= Long.toString(difference);
			
			out.println("<h1>Shipment Status</h1>");
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
			out.println("<td>Shipment Status</td>");
			
			if(i<=0){
				out.println("<td>Item Delivered</td>");
			}
			else if(i>0&&i<=14){
				out.println("<td>Item Shipped</td>");	
			}
			else{
				out.println("<td>Item Delivered</td>");
			}
			out.println("</tr>");
		
			out.println("</table>");
			} 	
			out.println("<a href='TrackOrder.html'>Go Back</a>");
			
			out.println("</body>");
			out.println("</html>");
	%>		