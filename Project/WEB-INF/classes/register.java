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
			out.println("<head><link rel='stylesheet' href='styles1.css' type='text/css' /></head>");
			out.println("<body>");
			out.println("<h1> Welcome"+ FirstName + "</h1>");
			out.println("<br/>");
			out.println("<a href='homepage1.html'> Click To Proceed </a>");
			out.println("</body>");
			out.println("</html>");
		
		} catch (MongoException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy()	{
      // do nothing.
	}
	
}