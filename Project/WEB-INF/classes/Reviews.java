import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.Date;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

public class Reviews extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	MongoClient mongo;
	
	public void init() throws ServletException{
     // Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	try{
		String productModelName = request.getParameter("itemName");
		String productCatagory = request.getParameter("itemType");
		String productPrice = request.getParameter("itemPrice");
		String retailerName = "GameSpeed";
		String retailerZip = "60616";
		String retailerCity = "Chicago";
		String retailerState = "IL";
		String productOnSale = "yes";
		String manufacturarName = "GalaxyGames";
		String manufacturarRebate = "yes";
						
		// if database doesn't exists, MongoDB will create it for you
		DB db = mongo.getDB("CSP595Tutorial");
			
		DBCollection myReviews = db.getCollection("myReviews");
			
		// Find and display 
		BasicDBObject searchQuery = new BasicDBObject();
			
		//searchQuery.put(productName, imageLocation);
		DBCursor cursor = myReviews.find(searchQuery);
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>Product Reviews</title><link rel='stylesheet' href='styl.css' type='text/css' /></head>");
		
		out.println("<body>");	
		out.println("<h1>Product Reviews</h1>");	
					
					
		out.println("");
		out.println("<h3>" +productModelName+ "</h3>");
		out.println("<form method='get' action='SubmitReview'>");
		out.println("<fieldset>");
		out.println("<legend>Product information:</legend>");
		//out.println("<table>");
		out.println("<table>");
		out.println("<tr>");
		out.println("<td> Product Model Name: </td>");
		out.println("<td> <input type='text' name='productModelName' value="+productModelName+" readonly> </td></tr>");
		out.println("<tr>");
		out.println("<td> Product Category: </td>");
		out.println("<td> <input type='text' name='productCatagory' value="+productCatagory+" readonly> </td></tr>");
		out.println("<tr><td> Product Price: </td>");
		out.println("<td> <input type='text' name='productPrice' value="+productPrice+" readonly> </td></tr>");
		out.println("<tr><td> Retailer Name: </td>");
		out.println("<td> <input type='text' name='retailerName' value="+retailerName+" readonly> </td></tr>");
		out.println("<tr><td> Retailer Zip: </td>");
		out.println("<td> <input type='text' name='retailerZip' value="+retailerZip+" readonly> </td></tr>");
		out.println("<tr><td> Retailer City: </td>");
		out.println("<td> <input type='text' name='retailerCity' value="+retailerCity+" readonly> </td></tr>");
		out.println("<tr><td> Retailer State: </td>");
		out.println("<td> <input type='text' name='retailerState' value="+retailerState+" readonly> </td></tr>");
		out.println("<tr><td> Product On Sale: </td>");
		out.println("<td> <input type='text' name='productOnSale' value="+productOnSale+" readonly> </td></tr>");
		out.println("<tr><td> Manufacturer Name: </td>");
		out.println("<td> <input type='text' name='manufacturarName' value="+manufacturarName+" readonly> </td></tr>");
		
		out.println("</table></fieldset>");
		out.println("<fieldset>");
		out.println("<legend>Personal information:</legend>");
		out.println("<table>");
		out.println("<tr><td> UserID </td><td> <input type='text' name='userId'> </td></tr>");
		out.println("<tr><td> User Age: </td><td> <input type='text' name='userAge'> </td></tr>");
		out.println("<tr><td> User Gender: </td><td> <input type='text' name='userGender'> </td></tr>");
		out.println("<tr><td> User Occupation: </td><td> <input type='text' name='userOccupation'> </td></tr>");
		out.println("<tr><td> Review Rating: </td><td><select name='reviewRating'><option value='1' selected>1</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></td> </td></tr>");
		out.println("<tr><td> Review Date: </td><td> <input type='date' name='reviewDate'> </td></tr>");
		out.println("<tr><td> Review Text: </td><td><textarea name='reviewText' rows='4' cols='50'> </textarea></td></tr>");
		out.println("</table>");
		out.println("<br><br>");
		out.println("<input type='submit' value='Submit'>");
		out.println("</fieldset>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}			
	catch (MongoException e) {
			e.printStackTrace();
	} 
	}

	
	public void destroy(){
      // do nothing.
	}
}