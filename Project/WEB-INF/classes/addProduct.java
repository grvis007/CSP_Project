import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;
import com.mongodb.*;

import java.util.*;
import java.io.*;

public class addProduct extends HttpServlet {
   
	public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException{
		
		String itemId = request.getParameter("itemId");
		String itemName = request.getParameter("itemName");
		String itemPrice = request.getParameter("itemPrice");
		String itemType = request.getParameter("itemType");
		String manufacturerRebate = request.getParameter("manufacturerRebate");
		String retailerDiscount = request.getParameter("retailerDiscount");
		String imagePath = request.getParameter("imagePath");
		
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
									
		// If database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("CSP595DB");
		
		// If the collection does not exists, MongoDB will create it for you
			DBCollection Products = db.getCollection("Products");
			//System.out.println("Collection products selected successfully");
				
			BasicDBObject doc = new BasicDBObject("title", "Products").
				append("itemid", itemId).
				append("itemname", itemName).
				append("itemprice", itemPrice).
				append("itemtype", itemType).
				append("manufacturerrebate", manufacturerRebate).
				append("retailerdiscount", retailerDiscount).
				append("imagepath", imagePath);
									
			Products.insert(doc);
		
		java.io.PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title></title>");  
				out.println("<link rel='stylesheet' href='styl5.css' type='text/css' />");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>"+ "Item added Successfully !!" +"</h1>");
				out.println("<h1>"+ itemId +"</h1>");
				out.println("<a href='storemanager.html'>Go back</a>");
				out.println("</body>");
				out.println("</html>");
				out.close();	   	
}	    	

} 