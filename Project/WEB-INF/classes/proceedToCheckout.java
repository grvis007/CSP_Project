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

public class proceedToCheckout extends HttpServlet {
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		
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
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='stylesheet' href='styl4.css' type='text/css' />");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='moveass'>");
			out.println("<h1>Place Order</h1>");
			out.println("<table border='2'>");
			out.println("<tr>");
				out.println("<th>Product Name</th>");
				out.println("<th>Price</th>");
				out.println("<th>Rebate</th>");
				out.println("<th>Discount</th>");
			out.println("</tr>");
			double total = 0.0;
		for(String key: product.keySet()){
			List<String> items = product.get(key);
			out.println("<tr>");
				out.println("<td>"+key+"</td>");
				out.println("<td>"+items.get(0)+"</td>");
				out.println("<td>"+items.get(1)+"</td>");
				out.println("<td>"+items.get(2)+"</td>");
				total = total+Double.parseDouble(items.get(0))-Double.parseDouble(items.get(1))-Double.parseDouble(items.get(2));
				out.println("<td><form method='get' action='deleteItem'><input type='hidden' name='key' value='"+key+"'><input type='hidden' name='productPrice' value='"+items.get(0)+"'><input type='hidden' name='rebate' value='"+items.get(1)+"'><input type='hidden' name='discount' value='"+items.get(2)+"'><input type='submit' value='delete'></form></td>");
			out.println("</tr>");
			
			
			
		} 		
			
			out.println("</table>");
			out.println("<hr/>");
			out.println("Total Amount:");
			out.println(total);
			out.println("<hr/>");
			out.println("<form method='get' action='placeOrder'><table><tr><td>Name:</td><br/><br/><td><input type='text' name='customerName'></td></tr><tr><td>Address:</td><td><input type='text' name='address'></td></tr><tr><td>Credit Card Number:</td><td><input type='text' name='card'></td></tr><tr><td>Phone No.:</td><td><input type='text' name='phone'></td></tr><tr><td></td><td><input type='submit' class='btn' value='Place Order'></td></tr></table></form>");
			//out.println("<form action='/Assignment1/console.html'><input type='submit' value='Add More Items'></form>");
			//out.println("<form method='get' action='proceedToCheckOut'><input type='submit' value='Proceed to Checkout'></form>");
			out.println("</div>");
			out.println("</body>");	
			out.println("</html>"); 
		
	}	
}