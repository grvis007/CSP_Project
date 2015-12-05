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
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

import java.util.HashMap;
import java.util.Map;

public class register extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try{
			//Get the values from the form
			String FirstName = request.getParameter("firstname");
			String LastName = request.getParameter("lastname");
			String userId = request.getParameter("email");
			String Password = request.getParameter("password");
			String Confirm_Password = request.getParameter("cpassword");
			String Phone = request.getParameter("phone");	
										
			// If database doesn't exists, MongoDB will create it for you
			DB db = mongo.getDB("Project");
				
			// If the collection does not exists, MongoDB will create it for you
			DBCollection users = db.getCollection("users");
			System.out.println("Collection users selected successfully");
				
			BasicDBObject doc = new BasicDBObject("title", "users").
				append("firstname", FirstName).
				append("lastname", LastName).
				append("userid", userId).
				append("password", Password).
				append("cpassword", Confirm_Password).
				append("phone", Phone);
									
			users.insert(doc);
			
			System.out.println("Document inserted successfully"); 
			
			//Send the response back to the JSP
			PrintWriter out = response.getWriter();
						
			out.println("<html>");
			//out.println("<head><link rel='stylesheet' href='styles1.css' type='text/css' /></head>");
			out.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>Chicago Art Shop</title><link rel='stylesheet' href='styl5.css' type='text/css' /><link rel='stylesheet' href='styl3.css' type='text/css' /></head>");
		
			out.println("<body>");
			out.println("<h1> Welcome "+ FirstName + "</h1>");
			out.println("<br/>");
			
			out.println("</body>");
			out.println("</html>");
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
		
		//out.println( "<li class=''><img src='C:/images/iPhone.jpg' alt='myimg'></li>");
		out.println("</ul>");
		//out.println("<ul>");
		//out.println("<div style='position:relative; height:60px;'><img src='images/cover.jpg' style='position:absolute; top:-513px; left:198px; width:84.5%; height:852%; border:none;'alt=''/></div>");
		out.println("<h3>Please, Click on any item to continue Shopping</h3>");
		
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}