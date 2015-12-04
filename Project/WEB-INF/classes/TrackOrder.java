import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;



import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TrackOrder extends HttpServlet {
	long difference=0;
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='stylesheet' href='styles1.css' type='text/css' />");
			
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
			String deliveryDate ="";
			while(cursor.hasNext()) {
				BasicDBObject searchObj = (BasicDBObject)cursor.next();
				deliveryDate = searchObj.getString("delivarydate");	
			}
			
			try{
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date dateObj = new Date();
				
				Date date1 = null;
				Date date2 = null;
				
				String today=dateFormat.format(dateObj);
				SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
 		
		
				date1 = format.parse(deliveryDate);
				date2 = format.parse(today);
			
				
 
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
			if(i<14){
				out.println("<td>Item Shipped</td>");	
			}
			else{
				out.println("<td>Item Delivered</td>");
			}
			out.println("</tr>");
		
			out.println("</table>");
			
			out.println("<a href='trackOrder.html'>Go Back</a>");
			
			out.println("</body>");
			out.println("</html>");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		
			
		}
		catch(MongoException m){
			m.printStackTrace();
		}
				
	}
}
	
	
