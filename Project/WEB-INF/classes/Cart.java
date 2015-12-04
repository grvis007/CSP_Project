
import java.util.HashMap;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import javax.servlet.ServletException;



	public class Cart {
    HashMap<String, List<String>> cartItems;
    public Cart(){
     cartItems = new HashMap<String, List<String>>();
      
    }
    public HashMap getCartItems(){
        return cartItems;
    }
    public void deleteFromCart(String itemId){
        cartItems.remove(itemId);
    }
	public void addToCart(String itemId, List<String> value){
        cartItems.put(itemId, value);
    }
   
}
     	
