import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.io.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mongodb.*;

public class addToCart1 extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>Chicago Art Shop</title><link rel='stylesheet' href='styl5.css' type='text/css' /><link rel='stylesheet' href='styl3.css' type='text/css' /></head>");
		out.println("<body>");
		
				
				
				
				out.println("<table class='moveit'>");
			    //out.println("<tr>");
				//out.println("<td></td><td></td><td><img src="+obj.getString("imagepath")+" alt='myimg' width=1700px height=150px ></td></tr><br/>");
				
				
				out.println("<tr>");
				//out.println("<td> Item Name: </td><td><input type='text' class = 'move' name='itemName' value ="+obj.getString("itemname")+" readonly ></td>");
				out.println("<td>");
				out.println("<form class = '' method = 'get' action = 'addToCart'>");
				out.println("<input class = 'move' type = 'submit' name = 'addToCart' value= 'Add to Cart '>");
				//out.println("<input  type = 'hidden' name = 'itemId' value = "+obj.getString("itemid")+" readonly>");
				//out.println("<input  type = 'hidden' name = 'itemName' value = "+obj.getString("itemname")+" readonly>");
				//out.println("<input  type = 'hidden' name = 'itemPrice' value = "+obj.getString("itemprice")+" readonly>");
				//out.println("<input  type = 'hidden' name = 'itemType' value = "+obj.getString("itemtype")+" readonly>");
				//out.println("<input  type = 'hidden' name = 'manufacturerRebate' value = "+obj.getString("manufacturerrebate")+" readonly>");
				//out.println("<input  type = 'hidden' name = 'retailerDiscount' value = "+obj.getString("retailerdiscount")+" readonly>");
				out.println("</form>");
			    //out.println("</td></tr>");
				
				
		
						
			}
			
			
		
		
		
		
		
			
	
	
}