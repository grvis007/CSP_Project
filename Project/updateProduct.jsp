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
	</head>
	<body>
	<%
		String itemId = request.getParameter("itemId");
		String itemName = request.getParameter("itemName");
		String itemType = request.getParameter("itemType");
		String itemPrice = request.getParameter("itemPrice");
		String manufacturerRebate = request.getParameter("itemRebate");
		String retailerDiscount = request.getParameter("itemDiscount");
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
									
		// If database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("Tutorial_3");
		
		// If the collection does not exists, MongoDB will create it for you
			DBCollection products = db.getCollection("products");
			//System.out.println("Collection products selected successfully");
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("itemid", itemId);
			products.remove(searchQuery);
			
				
			BasicDBObject doc = new BasicDBObject("title", "products").
				append("itemid", itemId).
				append("itemname", itemName).
				append("itemprice", itemPrice).
				append("itemtype", itemType).
				append("manufacturerrebate", manufacturerRebate).
				append("retailerdiscount", retailerDiscount);
									
			products.insert(doc);
	
	
	%>
	
	
	<h1> Item Updated Successfully </h1>
			<br/>
			<a href='storemanager.html'>Go back</a>
	</body>
	</html>