/*
 * LoginServlet.java
 *
 */
 

import java.util.HashMap;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

import java.net.UnknownHostException;
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

public class LoginServlet extends HttpServlet {
   
    //protected Map users = new HashMap();
    /** 
     * Initializes the servlet with some usernames/password
    */  
    
	
	private static final long serialVersionUID = 1L;
	MongoClient mongo;
	
	public void init() throws ServletException{
      	// Connect to Mongo DB
		mongo = new MongoClient("localhost", 27017);
	}
	
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
	try{	
		String userName = "";
		String password = "";
		
        String userId = request.getParameter("userid");
        String Password = request.getParameter("password");
		String userType = request.getParameter("usertype");
        
		//MongoClient mongo;
		//mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("Project");
		DBCollection users = db.getCollection("users");
				
		String storeManagerId = "xxx@gmail.com";
		String storeManagerPass = "xxx"; 
		String salesmanId = "yyy@gmail.com";
		String salesmanPass = "yyy"; 
		
		if(userType.equals("storemanager")){
			if(userId.equals(storeManagerId)&&Password.equals(storeManagerPass)){
				response.sendRedirect("storemanager.html");
			}
			else{
			response.setContentType("text/html");
				java.io.PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login Servlet Result</title>");  
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>"+ "Login Failed!!" +"</h2>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			}
		}
		else if(userType.equals("salesman")){
			if(userId.equals(salesmanId)&&Password.equals(salesmanPass)){
				response.sendRedirect("salesman.html");
			}
			else{
			response.setContentType("text/html");
				java.io.PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login Servlet Result</title>");  
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>"+ "Login Failed!!" +"</h2>");
				out.println("</body>");
				out.println("</html>");
				out.close();
			}
		}
		else if(userType.equals("customer")){
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("userid", userId);
			DBCursor cursor = users.find(searchQuery);
		
			while(cursor.hasNext()){
				BasicDBObject ob = (BasicDBObject)cursor.next();
				userName = ob.getString("userid");
				password = ob.getString("password");
				if(userName.equals(userId)&&password.equals(Password)){
					response.sendRedirect("homepage1.html");
				}
				else{
					response.setContentType("text/html");
					java.io.PrintWriter out = response.getWriter();
					out.println("<html>");
					out.println("<head>");
					out.println("<title>Login Servlet Result</title>");  
					out.println("</head>");
					out.println("<body>");
					out.println("<h2>"+ "Login Failed!!" +"</h2>");
					out.println("</body>");
					out.println("</html>");
					out.close();
				}
			}
		}
		else{
			response.setContentType("text/html");
				java.io.PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Login Servlet Result</title>");  
				out.println("</head>");
				out.println("<body>");
				out.println("<h2>"+ "Login Failed!!" +"</h2>");
				out.println("</body>");
				out.println("</html>");
				out.close();
		}				
	}catch (MongoException e) {
				e.printStackTrace();
	}
	}
}