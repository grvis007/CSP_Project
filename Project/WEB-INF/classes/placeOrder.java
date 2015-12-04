import java.io.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import java.util.Random;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class placeOrder extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
			
			String customerName = request.getParameter("customerName");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phone");
							
			Date sysDate = new Date();
			String orderDate = sysDate.toString(); 
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar c = Calendar.getInstance();
			//Date d = c.setTime(new Date()); 
			//String orderDate = d.toString();
			c.add(Calendar.DATE, 14); 
			String delivaryDate = sdf.format(c.getTime());
				
			Double total = 0.0;
			
			Random r = new Random( System.currentTimeMillis() );
			int rand= 1000 + r.nextInt(20000);
			String orderNo = Integer.toString(rand);
			
			
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("CSP595Tutorial");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection Purchase = db.getCollection("Purchase");
			System.out.println("Collection Purchase selected successfully");
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=UTF-8");
			
			String Key = request.getParameter("key");
			String productPrice = request.getParameter("productPrice");
			String rebate = request.getParameter("rebate");
			String discount = request.getParameter("discount");
			
			HttpSession session = request.getSession();
			Cart shoppingCart;
			shoppingCart = (Cart) session.getAttribute("Cart");
			session.setAttribute("Cart", shoppingCart);
			shoppingCart = (Cart) session.getAttribute("Cart");
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            HashMap<String, List<String>> product = shoppingCart.getCartItems();
			//double total =0.0;
			
			for(String key: product.keySet()){
				List<String> items = product.get(key);
				total = total+Double.parseDouble(items.get(0))-Double.parseDouble(items.get(1))-Double.parseDouble(items.get(2));
			}	
			
				BasicDBObject doc = new BasicDBObject("title", "Purchase").
				append("orderno", orderNo).
				append("orderdate", orderDate).
				append("delivarydate", delivaryDate).
				append("total", total).
				append("customername", customerName).
				append("address", address).
				append("phoneNumber", phoneNumber);
			Purchase.insert(doc);
					 
		 	
			response.setContentType("text/html;charset=UTF-8");
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='stylesheet' href='styl4.css' type='text/css' />");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='moveass'>");
			out.println("<h1>Your Receipt</h1>");
			out.println("<h1>Order Number:"+orderNo+"</h1>");
			out.println("<table border='2'>");
			
			out.println("<tr>");
			out.println("<td>Order Date</td>");
			out.println("<td>"+orderDate+"</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Expected Delivery Date</td>");
			out.println("<td>"+delivaryDate+"</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Total Amount</td>");
			out.println("<td>"+total+"</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Customer Name</td>");
			out.println("<td>"+customerName+"</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Address</td>");
			out.println("<td>"+address+"</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<td>Phone Number</td>");
			out.println("<td>"+phoneNumber+"</td>");
			out.println("</tr>");
		
			out.println("</table>");
			out.println("<a href='homepage1.html'>Back to Home</a>");
			out.println("</div>");
			out.println("</body>");	
			out.println("</html>");
			
			
	}
}