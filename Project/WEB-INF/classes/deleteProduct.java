import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.*;
import java.io.PrintWriter;

import java.util.*;
import java.io.*;
import java.io.FileReader;

public class deleteProduct extends HttpServlet {
   
	public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException{
	
		String itemId = request.getParameter("itemId");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		out.println("<!DOCTYPE html>");
        out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styl5.css' type='text/css' />");
		out.println("</head>");
		out.println("<body>");
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("CSP595DB");
		DBCollection Products = db.getCollection("Products");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("itemid", itemId);
		Products.remove(searchQuery);
		out.println("<h1>"+ "Item deleted Successfully !!" +"</h1>");
		out.println("<a href='storemanager.html'>Go back</a>");
		out.println("</body>");
		out.println("</html>");
				
}	
	
}	  	    
	    	


