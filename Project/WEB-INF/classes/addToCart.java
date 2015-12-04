import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.ServletException;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;


public class addToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
			// Connect to Mongo DB
			mongo = new MongoClient("localhost", 27017);
		}    
	
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
		String ItemName = request.getParameter("itemName");
		String ItemPrice = request.getParameter("itemPrice");
		String manufacturerRebate = request.getParameter("manufacturerRebate");
		String retailerDiscount = request.getParameter("retailerDiscount");
		
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
        Cart shoppingCart;
        shoppingCart = (Cart) session.getAttribute("Cart");
        if(shoppingCart == null){
          shoppingCart = new Cart();
          session.setAttribute("Cart", shoppingCart);
        }  
		List<String> features = new ArrayList();
		features.add(ItemPrice);
		features.add(manufacturerRebate);
		features.add(retailerDiscount);
		shoppingCart.addToCart(ItemName, features);
		session.setAttribute("Cart", shoppingCart);
		
		
		
		DB db = mongo.getDB("CSP595Tutorial");
		// If the collection does not exists, MongoDB will create it for you
		DBCollection myCart = db.getCollection("myCart");
		
		BasicDBObject doc = new BasicDBObject("title", "myCart").
			append("productModelName", ItemName).
			append("productPrice", ItemPrice).
			append("manufacturerRebate", manufacturerRebate).
			append("retailerDiscount", retailerDiscount);			
			myCart.insert(doc);
		
		System.out.println("Document inserted successfully");
		HashMap<String, List<String>> product = shoppingCart.getCartItems();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel='stylesheet' href='styl4.css' type='text/css' />");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class='moveass'>");
		out.println("<h1>Items in Cart</h1>");
		out.println("<table border='2'>");
			out.println("<tr>");
				out.println("<th>Product Name</th>");
				out.println("<th>Price</th>");
				out.println("<th>Rebate</th>");
				out.println("<th>Discount</th>");
				out.println("<th></th>");
			out.println("</tr>");
	
		for(String key: product.keySet()){
			List<String> items = product.get(key);
			out.println("<tr>");
				out.println("<td>"+key+"</td>");
				out.println("<td>"+items.get(0)+"</td>");
				out.println("<td>"+items.get(1)+"</td>");
				out.println("<td>"+items.get(2)+"</td>");
				out.println("<td><form method='get' action='deleteItem'><input type='hidden' name='key' value='"+key+"'><input type='hidden' name='productPrice' value='"+items.get(0)+"'><input type='hidden' name='rebate' value='"+items.get(1)+"'><input type='hidden' name='discount' value='"+items.get(2)+"'><input type='submit' value='delete'></form></td>");
			out.println("</tr>"); 
		} 		
		out.println("</table>");
		out.println("<br/>");
		out.println("<form action='/Project/homepage1.html'><input type='submit' class='btn' value='Add More Items'></form>");
		out.println("<form method='get' action='proceedToCheckout'><input type='submit' class='btn' value='Proceed to Checkout'></form>");		
		out.println("</div>");
		out.println("</body>");	
		out.println("</html>");
	}
	
}