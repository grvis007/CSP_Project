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

public class horrorshows extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>Chicago Art Shop</title><link rel='stylesheet' href='styl5.css' type='text/css' /><link rel='stylesheet' href='styl3.css' type='text/css' /></head>");
		out.println("<body>");
		out.println("<div id='container'>");
		out.println("<header >");
		out.println("<h1>Chicago Art Shop</a></h1><h3>Best Art in best price</h3>");
		out.println("<span ><ul class=''><li class=''><a href=''></a></li><li class=''><a href='#'></a></li><li class=''><a href='help.html'></a></li></ul></span></header>");
		out.println("<nav>"+
    	"<ul>"+
        	"<li class=''><a href='homepage1.html'>Home</a></li>"+
            "<li class=''><a href='Todaydeal'>Todays Deal</a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href=''></a></li>"+
			"<li class=''><a href='TrackOrder.html'>Track Order</a></li>"+
            "<li class=''><a href='help.html'>Help</a></li>"+               
			"<li class=''><a href=''><img src='images/cart2.png'></a></li>"+											
			"</li></ul></nav>");	
			//"<li class=''><a href='login.html'>Sign In</a></li><li class=''><a href='register.html'>Create Account</a></li></ul></nav>");	   
			   
		out.println("<div id='cssmenu'>");
		out.println("<ul>");
		out.println("<li class='active has-sub'><a href='#'>Movies</a>"+
				"<ul>"+
					"<li class=''><a href='classicmovies'>Classic Movies</a></li>"+
					"<li class=''><a href='AAmovies'>Action & Adventure Movies</a></li>"+
					"<li class=''><a href='comedymovies'>Comedy Movies</a></li>"+
					"<li class=''><a href='animatedmovies'>Animated Movies</a></li>"+
				"</ul>"+
			   "</li>");
		out.println("<li class='has-sub'><a href='#'>Music</a>"+
				"<ul>"+
				
					"<li class=''><a href='popmusic'>Pop Music</a></li>"+
					"<li class=''><a href='fwmusic'>Folk & World Music</a></li>"+
					"<li class=''><a href='rhiphopmusic'>Rap & Hip Hop</a></li>"+
					"<li class=''><a href='countrymusic'>Country Music</a></li>"+
				"</ul>"+
					
			   "</li>");
			   
	    out.println("<li class='has-sub'><a href='#'>Television</a>"+
				"<ul>"+
				
					"<li class=''><a href='actionshows'>Action TV Shows</a></li>"+
					"<li class=''><a href='horrorshows'>Horror TV Shows</a></li>"+
					"<li class=''><a href='dramashows'>Drama TV Shows</a></li>"+
					"<li class=''><a href='cartoonshows'>Cartoon TV Shows</a></li>"+
				"</ul>"+
					
			   "</li>");
		
		
		out.println("<li class='has-sub'><a href='#'>Sports</a>"+
				"<ul>"+
				
					"<li class=''><a href='football'>Football</a></li>"+
					"<li class=''><a href='cricket'>Cricket</a></li>"+
					"<li class=''><a href='basketball'>Basket Ball</a></li>"+
					"<li class=''><a href='soccer'>Soccer</a></li>"+
				"</ul>"+
					
			   "</li>");
			   
	    out.println("<li class='has-sub'><a href='#'>Motivational</a>"+
				"<ul>"+
				
					"<li class=''><a href='mwoodsigns'>Motivational Wood Signs</a></li>"+
					"<li class=''><a href='mwallstickers'>Motivational Wall Stickers</a></li>"+
					"<li class=''><a href='mgiantposters'>Motivational Giant Posters</a></li>"+
					
				"</ul>"+
					
			   "</li>");
	    out.println( "<li class='has-sub'><a href='#'>Educational</a>"+
				"<ul>"+
					"<li class=''><a href='#'>Maps of North America</a>"+
						"<ul>"+
							"<li class=''><a href='usa'>USA</a></li>"+
							"<li class=''><a href='canada'>Canada</a></li>"+
						"</ul>"+
					"</li>"+
					"<li class=''><a href='#'>Maps of Asia</a>"+
				"<ul>"+
				
					"<li class=''><a href='china'>China</a></li>"+
					"<li class=''><a href='india'>India</a></li>"+
					"<li class=''><a href='japan'>Japan</a></li>"+
					"<li class=''><a href='iran'>Iran</a></li>"+
					
				"</ul>"+
					
			   "</li>"+
					
					"<li class=''><a href='#'>Maps of Europe</a>"+
						"<ul>"+
							"<li class=''><a href='uk'>UK</a></li>"+
							"<li class=''><a href='germany'>Germany</a></li>"+
							"<li class=''><a href='france'>France</a></li>"+
							"<li class=''><a href='italy'>Italy</a></li>"+
							"<li class=''><a href='spain'>Spain</a></li>"+
						"</ul>"+
					"</li>"+	
				"</ul>"+
			   "</li>");
			   
	     out.println( "<li class=''><a href='photography'>Photography</a></li>");
		out.println( "<li class='has-sub'><a href='#'>Video Games</a>"+
				"<ul>"+
				"<li class=''><a href='angrybird'>Angry Birds</a></li>"+
							"<li class=''><a href='battlefield3'>Battlefield 3</a></li>"+
							"<li class=''><a href='battlefield4'>Battlefield 4</a></li>"+
							"<li class=''><a href='callofduty'>Call of Duty: Ghosts</a></li>"+
					
				"</ul>"+
			   "</li>");
			   
	    out.println("<li class='has-sub'><a href='#'>People</a>"+
			   "<ul>"+
				"<li class=''><a href='indiancelebraty'>Indian Celebrities</a></li>"+
							"<li class=''><a href='frenchcelebraty'>French Celebrities</a></li>"+
							"<li class=''><a href='americancelebraty'>American Celebrities</a></li>"+
							
					
				"</ul>"+
			   "</li>");
			   
	    out.println( "<li class='has-sub'><a href='#'>Comics</a>"+
			   "<ul>"+
				"<li class=''><a href='marvelcomics'>Marvel Comics</a></li>"+
							"<li class=''><a href='dccomics'>DC Comics</a></li>"+
							"<li class=''><a href='threedcomics'>3D Comics Posters</a></li>"+
							
					
				"</ul>"+
			   "</li>");
		out.println( "<li class='has-sub'><a href='#'>Humour</a>"+
			   "<ul>"+
				"<li class=''><a href='animalhumor'>Animal Humour</a></li>"+
							"<li class=''><a href='bathroomhumor'>Bathroom Humour</a></li>"+
							
							
					
				"</ul>"+
			   "</li>");
		//out.println( "<li class=''><a href='#'>More Products</a></li>");
		//out.println( "<li class=''><img src='C:/images/iPhone.jpg' alt='myimg'></li>");
		out.println("</ul>");
		//out.println("<ul>");
		//out.println( "<li class=''><img src='C:/images/iPhone.jpg' alt='myimg'></li>");
		//out.println("</ul>");
		
		out.println("<footer>"+
		"<nav>"+
			"<ul>"+
				/*"<li class=''><a href=''>About Us</a></li>"+
				"<li class=''><a href='#'>Contact Us</a></li>"+
				
				"<li class=''><a href='#'>Careers</a></li>"+*/
				"<li class='myimg'><img src='C:/images/iPhone.jpg' alt='myimg'></li>"+
				//"<li class=''><a href='register.html'>Terms & Conditions</a></li>"+
				
			"</ul>"+
		"</nav>"+
		"</footer>");
		String searchParameter = "Horror_Shows";
		String searchField = "itemtype";
				
		MongoClient mongo;
		mongo = new MongoClient("localhost", 27017);
				
				// if database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("CSP595DB");
			
		DBCollection Products = db.getCollection("Products");
		BasicDBObject searchQuery = new BasicDBObject();
				searchQuery.put(searchField, searchParameter);
			
				DBCursor cursor = Products.find(searchQuery);
				
				while(cursor.hasNext()){
				BasicDBObject obj = (BasicDBObject) cursor.next();
				
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				//out.println("<table class='moveit'>");
				//out.println("<tr><td></td>");
				//out.println("<td><img src="+obj.getString("imagepath")+" alt='myimg'  class='image1'></td>");
				//out.println("</tr></table>");
				out.println("<img src="+obj.getString("imagepath")+" alt='myimg'  class='image1'>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				out.println("<br/>");
				
				out.println("<table class='moveit'>");
			    //out.println("<tr>");
				//out.println("<td></td><td></td><td><img src="+obj.getString("imagepath")+" alt='myimg' width=1700px height=150px ></td></tr><br/>");
				
				
				out.println("<tr>");
				out.println("<td> Item Name: </td><td><input type='text' class = 'move' name='itemName' value ="+obj.getString("itemname")+" readonly ></td>");
				out.println("<td>");
				out.println("<form class = '' method = 'get' action = 'addToCart'>");
				out.println("<input class = 'move' type = 'submit' name = 'addToCart' value= 'Add to Cart '>");
				out.println("<input  type = 'hidden' name = 'itemId' value = "+obj.getString("itemid")+" readonly>");
				out.println("<input  type = 'hidden' name = 'itemName' value = "+obj.getString("itemname")+" readonly>");
				out.println("<input  type = 'hidden' name = 'itemPrice' value = "+obj.getString("itemprice")+" readonly>");
				out.println("<input  type = 'hidden' name = 'itemType' value = "+obj.getString("itemtype")+" readonly>");
				out.println("<input  type = 'hidden' name = 'manufacturerRebate' value = "+obj.getString("manufacturerrebate")+" readonly>");
				out.println("<input  type = 'hidden' name = 'retailerDiscount' value = "+obj.getString("retailerdiscount")+" readonly>");
				out.println("</form>");
			    out.println("</td></tr>");
				
				out.println("<tr>");
				out.println("<td> Item Price: </td><td><input type='text' class = 'move' name='itemPrice' value ="+obj.getString("itemprice")+" readonly ></td>");
				out.println("<td>");
				out.println("<form class = '' method = 'post' action = 'Reviews'>");
				out.println("<input class = 'move' type = 'submit' name = 'writeReview' value = 'Write Review'>");
				out.println("<input  type = 'hidden' name = 'itemId' value = "+obj.getString("itemid")+">");
				out.println("<input  type = 'hidden' name = 'itemName' value = "+obj.getString("itemname")+">");
				out.println("<input  type = 'hidden' name = 'itemPrice' value = "+obj.getString("itemprice")+">");
				out.println("<input  type = 'hidden' name = 'itemType' value = "+obj.getString("itemtype")+">");
				out.println("</form>");
				out.println("</td></tr>");
				
				out.println("<tr>");
				out.println("<td> Item Type: </td><td><input type='text' class = 'move' name='itemType' value ="+obj.getString("itemtype")+" readonly ></td>");
				out.println("<td>");
				out.println("<form class = '' method = 'get' action = 'ViewReviews'>");
				out.println("<input class = 'move' type = 'submit' name = 'viewReviews' value = 'View Reviews'>");
				out.println("<input  type = 'hidden' name = 'itemId' value = "+obj.getString("itemid")+">");
				out.println("<input  type = 'hidden' name = 'itemName' value = "+obj.getString("itemname")+">");
				out.println("<input  type = 'hidden' name = 'itemPrice' value = "+obj.getString("itemprice")+">");
				out.println("<input  type = 'hidden' name = 'itemType' value = "+obj.getString("itemtype")+">");
				out.println("</form>");
			    out.println("</td></tr>");				
				out.println("</table>");
				out.println("<br/>");
				out.println("<hr/>");
					
				

		
						
			}
			
			
		
		
		
		out.println("</body>");
		out.println("</html>");
		
			
	}
	
}